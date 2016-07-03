package Pojo;

/**
 * Created by rspl-nishant on 28/5/16.
 */
public class DoctorPojo {

    String DoctorName;
    String DoctorSpeciality;

    public DoctorPojo(String doctorName, String doctorSpeciality, String doctorQualification) {
        DoctorName = doctorName;
        DoctorSpeciality = doctorSpeciality;
        DoctorQualification = doctorQualification;
    }

    String DoctorQualification;

    public DoctorPojo() {

    }


    public String getDoctorQualification() {
        return DoctorQualification;
    }

    public void setDoctorQualification(String doctorQualification) {
        DoctorQualification = doctorQualification;
    }

    public String getDoctorSpeciality() {
        return DoctorSpeciality;
    }

    public void setDoctorSpeciality(String doctorSpeciality) {
        DoctorSpeciality = doctorSpeciality;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }






}
