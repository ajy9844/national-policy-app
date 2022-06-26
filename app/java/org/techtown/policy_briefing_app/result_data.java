package org.techtown.policy_briefing_app;

public class result_data {

    private int iv_profile;
    private String service_name;
    private String service_dsp;
    private String age_info;


    public result_data(String service_name, String service_dsp, String age_info) {

        this.service_name = service_name;
        this.service_dsp = service_dsp;
        this.age_info = age_info;
    }



    public String getService_name() {
        return service_name;
    }

    public String getService_dsp() {
        return service_dsp;
    }

    public String getAge_info() {
        return age_info;
    }



    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public void setService_dsp(String service_dsp) {
        this.service_dsp = service_dsp;
    }

    public void setAge_info(String age_info) {
        this.age_info = age_info;
    }
}
