package com.moonway.dto;

public class UserDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.id
     *
     * @mbg.generated Mon Feb 08 23:19:00 CST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.name
     *
     * @mbg.generated Mon Feb 08 23:19:00 CST 2021
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.gender
     *
     * @mbg.generated Mon Feb 08 23:19:00 CST 2021
     */
    private Byte gender;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.age
     *
     * @mbg.generated Mon Feb 08 23:19:00 CST 2021
     */
    private Integer age;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.mobile
     *
     * @mbg.generated Mon Feb 08 23:19:00 CST 2021
     */
    private String mobile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.register_mode
     *
     * @mbg.generated Mon Feb 08 23:19:00 CST 2021
     */
    private String registerMode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.third_party_id
     *
     * @mbg.generated Mon Feb 08 23:19:00 CST 2021
     */
    private String thirdPartyId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.id
     *
     * @return the value of tb_user.id
     *
     * @mbg.generated Mon Feb 08 23:19:00 CST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.id
     *
     * @param id the value for tb_user.id
     *
     * @mbg.generated Mon Feb 08 23:19:00 CST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.name
     *
     * @return the value of tb_user.name
     *
     * @mbg.generated Mon Feb 08 23:19:00 CST 2021
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.name
     *
     * @param name the value for tb_user.name
     *
     * @mbg.generated Mon Feb 08 23:19:00 CST 2021
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.gender
     *
     * @return the value of tb_user.gender
     *
     * @mbg.generated Mon Feb 08 23:19:00 CST 2021
     */
    public Byte getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.gender
     *
     * @param gender the value for tb_user.gender
     *
     * @mbg.generated Mon Feb 08 23:19:00 CST 2021
     */
    public void setGender(Byte gender) {
        this.gender = gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.age
     *
     * @return the value of tb_user.age
     *
     * @mbg.generated Mon Feb 08 23:19:00 CST 2021
     */
    public Integer getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.age
     *
     * @param age the value for tb_user.age
     *
     * @mbg.generated Mon Feb 08 23:19:00 CST 2021
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.mobile
     *
     * @return the value of tb_user.mobile
     *
     * @mbg.generated Mon Feb 08 23:19:00 CST 2021
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.mobile
     *
     * @param mobile the value for tb_user.mobile
     *
     * @mbg.generated Mon Feb 08 23:19:00 CST 2021
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.register_mode
     *
     * @return the value of tb_user.register_mode
     *
     * @mbg.generated Mon Feb 08 23:19:00 CST 2021
     */
    public String getRegisterMode() {
        return registerMode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.register_mode
     *
     * @param registerMode the value for tb_user.register_mode
     *
     * @mbg.generated Mon Feb 08 23:19:00 CST 2021
     */
    public void setRegisterMode(String registerMode) {
        this.registerMode = registerMode == null ? null : registerMode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.third_party_id
     *
     * @return the value of tb_user.third_party_id
     *
     * @mbg.generated Mon Feb 08 23:19:00 CST 2021
     */
    public String getThirdPartyId() {
        return thirdPartyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.third_party_id
     *
     * @param thirdPartyId the value for tb_user.third_party_id
     *
     * @mbg.generated Mon Feb 08 23:19:00 CST 2021
     */
    public void setThirdPartyId(String thirdPartyId) {
        this.thirdPartyId = thirdPartyId == null ? null : thirdPartyId.trim();
    }
}