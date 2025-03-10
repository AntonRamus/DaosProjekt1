package src.storage;

import src.application.models.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

//Gruppe 2 - Anton, Sidse og Victor
public class Storage {
    private static Connection minConnection;

    private Storage() {
        //can't initialize static class
    }

    static {
        try {
            String connectionString = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=ProjektDaos;encrypt=true;trustServerCertificate=true";
            String userId = "sa";
            String password = "GaoY3vkXyG";
            minConnection = DriverManager.getConnection(connectionString, userId, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Education> getEducations() {
        ArrayList<Education> educations = new ArrayList<>();
        try {
            Statement statement = minConnection.createStatement();
            ResultSet res = statement.executeQuery("select * from education");

            while (res.next()) {
                int id = res.getInt(1);
                String name = res.getString(2);
                Education education = new Education(id, name);
                educations.add(education);
            }
            res.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Fejl: " + e.getMessage());
        }

        return educations;
    }

    public static ArrayList<Student> getStudentsOnEducation(Education education) {
        ArrayList<Student> students = new ArrayList<>();
        int educationId = education.getEducationId();

        try {
            PreparedStatement pst = minConnection.prepareStatement("select * from student where educationID = ?");
            pst.setInt(1, educationId);
            ResultSet res = pst.executeQuery();

            while (res.next()) {
                int id = res.getInt(1);
                String name = res.getString(2);
                String active = res.getString(3);
                boolean isActive = active.equals("yes");
                Student student = new Student(id, name, isActive);
                students.add(student);
            }
            res.close();
            pst.close();

        } catch (SQLException e) {
            System.out.println("Fejl: " + e.getMessage());
        }
        return students;
    }

    public static ArrayList<Exam> getExamsOnEducation(Education education) {
        ArrayList<Exam> exams = new ArrayList<>();
        int educationId = education.getEducationId();

        try {
            PreparedStatement pst = minConnection.prepareStatement("select * from exam where educationID = ?");
            pst.setInt(1, educationId);
            ResultSet res = pst.executeQuery();

            while (res.next()) {
                int id = res.getInt(1);
                String name = res.getString(2);
                String type = res.getString(3);
                Exam exam = new Exam(id, name, type, education);
                exams.add(exam);
            }
            res.close();
            pst.close();

        } catch (SQLException e) {
            System.out.println("Fejl: " + e.getMessage());
        }
        return exams;
    }


    //Opgave 8a
    public static ExamTries createExamTry(int studentId, int examinationId, String grade, LocalDate date) {
        ExamTries examTry = null;
        try {
            //find det højeste id på examtries og læg 1 til for at få id til ny examtry
            Statement statement = minConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("select max(id) from examTries");
            resultSet.next();
            int examTryId = resultSet.getInt(1) + 1;
            resultSet.close();
            statement.close();

            //insert ny examtry
            PreparedStatement pst = minConnection.prepareStatement("insert into examTries values (?, ?, ?, ?, ?)");
            pst.setInt(1, examTryId);
            pst.setString(2, grade);
            pst.setString(3, date.toString());
            pst.setInt(4, studentId);
            pst.setInt(5, examinationId);

            pst.executeUpdate();
            examTry = new ExamTries(examTryId, grade, date);

            pst.close();

        } catch (SQLException e) {
            if (e.getErrorCode() == 547) {
                System.out.println("student ID eller examinations ID forkert");
            }
        }

        return examTry;
    }

    //Opgave 8b
    public static Examination createExamination(String termin, LocalDate startTime, LocalDate endTime, boolean stopTest, Exam exam) {
        Examination examination = null;
        try {
            //find det højeste id på examination og læg 1 til for at få id til ny examination
            Statement statement = minConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("select max(id) from Examination");
            resultSet.next();
            int examinationId = resultSet.getInt(1) + 1;
            resultSet.close();
            statement.close();

            //insert ny examtry
            PreparedStatement pst = minConnection.prepareStatement("insert into Examination values (?, ?, ?, ?, ?, ?)");
            pst.setInt(1, examinationId);
            pst.setString(2, startTime.toString());
            pst.setString(3, endTime.toString());
            pst.setString(4, termin);
            pst.setString(5, stopTest ? "yes" : "no");
            pst.setInt(6, exam.getExamId());


            pst.executeUpdate();
            examination = new Examination(examinationId, startTime, endTime, stopTest, termin);

            pst.close();

        } catch (SQLException e) {
            System.out.println("fejl: " + e.getMessage());
        }

        return examination;
    }

    //Opgave 8c
    public static ArrayList<String> getStudentGradesOnExamination(Exam exam, Examination examination) {
        ArrayList<String> students = new ArrayList<>();
        String sql = """
                select s.id, s.studentName, et.grade from student s
                inner join examTries et on s.id = et.StudentID
                inner join examination em on et.examinationID = em.id
                inner join exam e on em.examID = e.id
                where e.id = ? and em.term = ?
                """;
        try {
            PreparedStatement pst = minConnection.prepareStatement(sql);
            pst.setInt(1, exam.getExamId());
            pst.setString(2, examination.getTerm());

            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                int studentId = resultSet.getInt(1);
                String studentName = resultSet.getString(2);
                String studentGrade = resultSet.getString(3);

                String student = String.format("Id: %d \t Navn: %-20s Karakter: %s%n", studentId, studentName, studentGrade);
                students.add(student);
            }

        } catch (SQLException e) {
            System.out.println("fejl: " + e.getMessage());
        }
        return students;
    }

    public static ArrayList<Examination> getExaminationsOnExam(Exam exam) {
        ArrayList<Examination> examinations = new ArrayList<>();
        int examId = exam.getExamId();

        try {
            PreparedStatement pst = minConnection.prepareStatement("select * from examination where examID = ?");
            pst.setInt(1, examId);
            ResultSet res = pst.executeQuery();

            while (res.next()) {
                int id = res.getInt(1);
                String[] startDate = res.getString(2).split("-");
                String[] endDate = res.getString(3).split("-");
                String term = res.getString(4);
                boolean stopTest = res.getString(5).equals("yes");
                Examination examination = new Examination(id,
                        LocalDate.of(Integer.parseInt(startDate[0]), Integer.parseInt(startDate[1]), Integer.parseInt(startDate[2])),
                        LocalDate.of(Integer.parseInt(endDate[0]), Integer.parseInt(endDate[1]), Integer.parseInt(endDate[2])),
                        stopTest, term);
                examinations.add(examination);
            }
            res.close();
            pst.close();

        } catch (SQLException e) {
            System.out.println("Fejl: " + e.getMessage());
        }
        return examinations;
    }

}
