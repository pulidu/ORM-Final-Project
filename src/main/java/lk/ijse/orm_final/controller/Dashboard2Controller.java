package lk.ijse.orm_final.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.orm_final.bo.BOFactory;
import lk.ijse.orm_final.bo.custom.DashboardBO;
import lk.ijse.orm_final.dto.StudentDTO;
import lk.ijse.orm_final.tdm.StudyAllStudentTm;
import org.hibernate.Session;
import org.hibernate.Transaction;
import lk.ijse.orm_final.db.FactoryConfiguration;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class Dashboard2Controller {

    @FXML
    private BarChart<String, Number> BarChartStu;

    @FXML
    private Label lblIncom;

    @FXML
    private Label lblTotalInstructor;

    @FXML
    private Label lblTotalPrograms;

    @FXML
    private Label lblTotalStudent;

    @FXML
    private Label lblStudentCount;

    @FXML
    private TableView<StudyAllStudentTm> tblStudyAll;

    @FXML
    private TableColumn<StudyAllStudentTm, String> colId;

    @FXML
    private TableColumn<StudyAllStudentTm, String> colName;

    @FXML
    private TableColumn<StudyAllStudentTm, String> colDate;

    @FXML
    private AnchorPane dashboardForm;

    private final DashboardBO dashboardBO = (DashboardBO) BOFactory.getBO(BOFactory.BOType.DASHBOARD);

    @FXML
    public void initialize() {
        setCellValueFactory();
        setTotals();
        loadTableData();
        loadStudentChart();
        loadIncome();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
    }

    private void setTotals() {
        lblTotalPrograms.setText(String.valueOf(dashboardBO.getCulinaryProgramCount()));
        lblTotalStudent.setText(String.valueOf(dashboardBO.getStudentCount()));
        lblTotalInstructor.setText(String.valueOf(dashboardBO.getInstructorCount()));
    }

    private void loadTableData() {
        tblStudyAll.getItems().clear();
        ObservableList<StudyAllStudentTm> studentTms = FXCollections.observableArrayList();
        List<StudentDTO> allProgramStudents = dashboardBO.getAllProgramStudents();

        for (StudentDTO studentDTO : allProgramStudents) {
            studentTms.add(new StudyAllStudentTm(
                    studentDTO.getStudentId(),
                    studentDTO.getName(),
                    (Date) studentDTO.getRegistrationDate()
            ));
        }
        tblStudyAll.setItems(studentTms);
        lblStudentCount.setText(String.valueOf(studentTms.size()));
    }

    private void loadStudentChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Student Registrations");

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            List<Object[]> results = session.createQuery(
                            "SELECT MONTH(s.registrationDate), COUNT(s) " +
                                    "FROM Student s " +
                                    "GROUP BY MONTH(s.registrationDate)", Object[].class)
                    .list();

            for (Object[] row : results) {
                Integer month = ((Number) row[0]).intValue();
                Integer count = ((Number) row[1]).intValue();
                String monthName = LocalDate.of(LocalDate.now().getYear(), month, 1)
                        .getMonth()
                        .getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
                series.getData().add(new XYChart.Data<>(monthName, count));
            }

            BarChartStu.getData().clear();
            BarChartStu.getData().add(series);

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load student chart.").show();
        } finally {
            session.close();
        }
    }

    private void loadIncome() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();




        try {
            int currentMonth = LocalDate.now().getMonthValue();
            int currentYear = LocalDate.now().getYear();

            Double totalIncome = (Double) session.createNativeQuery(
                            "SELECT SUM(amount) FROM payments " +
                                    "WHERE MONTH(paymentDate) = :month AND YEAR(paymentDate) = :year")
                    .setParameter("month", currentMonth)
                    .setParameter("year", currentYear)
                    .uniqueResult();

            if (totalIncome == null) totalIncome = 0.0;

            lblIncom.setText(String.format("%.2f", totalIncome));

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load current month income").show();
        } finally {
            session.close();
        }
    }
}