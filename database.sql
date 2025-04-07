-- 创建数据库
CREATE DATABASE IF NOT EXISTS hospital;
USE hospital;

-- 用户信息表
DROP TABLE IF EXISTS user_info;
CREATE TABLE user_info (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    account VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(128) NOT NULL,
    role ENUM('医生', '护士', '系统管理员') NOT NULL,
    name VARCHAR(50) NOT NULL,
    status ENUM('正常', '禁用') NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    creator_id INT,
    phone_number VARCHAR(20) UNIQUE,
    FOREIGN KEY (creator_id) REFERENCES user_info(user_id)
);

-- 角色权限表
DROP TABLE IF EXISTS role_permission;
CREATE TABLE role_permission (
    role ENUM('医生', '护士', '系统管理员') PRIMARY KEY,
    permissions TEXT
);

-- 验证码表
DROP TABLE IF EXISTS verification_code;
CREATE TABLE verification_code (
    code_id INT PRIMARY KEY AUTO_INCREMENT,
    phone_number VARCHAR(20),
    code VARCHAR(6) NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_used BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (phone_number) REFERENCES user_info(phone_number)
);

-- 患者信息表
DROP TABLE IF EXISTS patient_info;
CREATE TABLE patient_info (
    patient_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    gender ENUM('男', '女') NOT NULL,
    id_card VARCHAR(18) UNIQUE,
    birth_date DATE NOT NULL,
    registration_date DATETIME NOT NULL,
    bed_number VARCHAR(20) NOT NULL,
    care_grade ENUM('一级护理', '二级护理', '三级护理') NOT NULL,
    admission_number VARCHAR(50) UNIQUE,
    insurance_type VARCHAR(20),
    insurance_id VARCHAR(50),
    contact_name VARCHAR(50),
    contact_phone VARCHAR(20)
);

-- 护士信息表
DROP TABLE IF EXISTS nurse_info;
CREATE TABLE nurse_info (
    nurse_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    department VARCHAR(50) NOT NULL
);

-- 医生信息表
DROP TABLE IF EXISTS doctor_info;
CREATE TABLE doctor_info (
    doctor_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    department VARCHAR(50) NOT NULL
);

-- 药品分类表
DROP TABLE IF EXISTS drug_category;
CREATE TABLE drug_category (
    category_id INT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(50) UNIQUE NOT NULL
);

-- 药品表
DROP TABLE IF EXISTS drug_info;
CREATE TABLE drug_info (
    drug_id INT PRIMARY KEY AUTO_INCREMENT,
    drug_name VARCHAR(100) UNIQUE NOT NULL,
    specification VARCHAR(50),
    category_id INT,
    contraindications TEXT,
    FOREIGN KEY (category_id) REFERENCES drug_category(category_id)
);

-- 诊断字典表
DROP TABLE IF EXISTS diagnosis_dict;
CREATE TABLE diagnosis_dict (
    diagnosis_id INT PRIMARY KEY AUTO_INCREMENT,
    diagnosis_name VARCHAR(100) UNIQUE NOT NULL
);

-- 疾病字典表
DROP TABLE IF EXISTS disease_dict;
CREATE TABLE disease_dict (
    disease_id INT PRIMARY KEY AUTO_INCREMENT,
    disease_name VARCHAR(100) UNIQUE NOT NULL
);

-- 报告类型字典表
DROP TABLE IF EXISTS report_type_dict;
CREATE TABLE report_type_dict (
    type_id INT PRIMARY KEY AUTO_INCREMENT,
    type_name VARCHAR(50) UNIQUE NOT NULL
);

-- 频次字典表
DROP TABLE IF EXISTS frequency_dict;
CREATE TABLE frequency_dict (
    freq_id INT PRIMARY KEY AUTO_INCREMENT,
    freq_code VARCHAR(10) UNIQUE NOT NULL,
    freq_description VARCHAR(50) NOT NULL
);

-- 饮食禁忌类型表
DROP TABLE IF EXISTS diet_restriction_type;
CREATE TABLE diet_restriction_type (
    type_id INT PRIMARY KEY AUTO_INCREMENT,
    type_name VARCHAR(50) UNIQUE NOT NULL
);

-- 照护项目表
DROP TABLE IF EXISTS care_project;
CREATE TABLE care_project (
    project_id INT PRIMARY KEY AUTO_INCREMENT,
    project_name VARCHAR(50) UNIQUE NOT NULL
);

-- 离院交接项目表
DROP TABLE IF EXISTS handover_item_dict;
CREATE TABLE handover_item_dict (
    item_id INT PRIMARY KEY AUTO_INCREMENT,
    item_name VARCHAR(50) UNIQUE NOT NULL
);

-- 剂量单位表
DROP TABLE IF EXISTS dosage_unit_dict;
CREATE TABLE dosage_unit_dict (
    unit_id INT PRIMARY KEY AUTO_INCREMENT,
    unit_name VARCHAR(20) UNIQUE NOT NULL
);

-- 医嘱操作类型表
DROP TABLE IF EXISTS operation_type_dict;
CREATE TABLE operation_type_dict (
    type_id INT PRIMARY KEY AUTO_INCREMENT,
    type_name VARCHAR(20) UNIQUE NOT NULL
);

-- 病史类型表
DROP TABLE IF EXISTS history_type_dict;
CREATE TABLE history_type_dict (
    type_id INT PRIMARY KEY AUTO_INCREMENT,
    type_name VARCHAR(20) UNIQUE NOT NULL
);

-- 入院评估主表
DROP TABLE IF EXISTS admission_assessment;
CREATE TABLE admission_assessment (
    assessment_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT,
    nurse_id INT,
    doctor_id INT,
    assessment_date DATE NOT NULL,
    health_assessment_status ENUM('待评估', '未完成', '完成'),
    care_assessment_status ENUM('待评估', '未完成', '完成'),
    FOREIGN KEY (patient_id) REFERENCES patient_info(patient_id),
    FOREIGN KEY (nurse_id) REFERENCES nurse_info(nurse_id),
    FOREIGN KEY (doctor_id) REFERENCES doctor_info(doctor_id)
);

-- 患者评估信息表
DROP TABLE IF EXISTS patient_assessment;
CREATE TABLE patient_assessment (
    id INT PRIMARY KEY AUTO_INCREMENT,
    forbidden_medicines TEXT,
    physical_conclusion TEXT NOT NULL,
    admission_agreement ENUM('同意入院', '不同意入院') NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 现病史表
DROP TABLE IF EXISTS current_illness;
CREATE TABLE current_illness (
    id INT PRIMARY KEY AUTO_INCREMENT,
    assessment_id INT,
    disease_id INT,
    FOREIGN KEY (assessment_id) REFERENCES admission_assessment(assessment_id),
    FOREIGN KEY (disease_id) REFERENCES disease_dict(disease_id)
);

-- 既往病史表
DROP TABLE IF EXISTS past_illness;
CREATE TABLE past_illness (
    id INT PRIMARY KEY AUTO_INCREMENT,
    assessment_id INT,
    disease_id INT,
    FOREIGN KEY (assessment_id) REFERENCES admission_assessment(assessment_id),
    FOREIGN KEY (disease_id) REFERENCES disease_dict(disease_id)
);

-- 饮食禁忌表
DROP TABLE IF EXISTS diet_restriction;
CREATE TABLE diet_restriction (
    id INT PRIMARY KEY AUTO_INCREMENT,
    assessment_id INT,
    type_id INT,
    FOREIGN KEY (assessment_id) REFERENCES admission_assessment(assessment_id),
    FOREIGN KEY (type_id) REFERENCES diet_restriction_type(type_id)
);

-- 检查报告表
DROP TABLE IF EXISTS medical_report;
CREATE TABLE medical_report (
    id INT PRIMARY KEY AUTO_INCREMENT,
    assessment_id INT,
    report_type INT,
    file_path VARCHAR(255) NOT NULL,
    file_format ENUM('jpg', 'png') NOT NULL,
    FOREIGN KEY (assessment_id) REFERENCES admission_assessment(assessment_id),
    FOREIGN KEY (report_type) REFERENCES report_type_dict(type_id)
);

-- 照护评估表
DROP TABLE IF EXISTS care_assessment;
CREATE TABLE care_assessment (
    id INT PRIMARY KEY AUTO_INCREMENT,
    assessment_id INT,
    project_id INT,
    risk_level ENUM('正常', '轻度', '中度', '重度') NOT NULL,
    care_evaluation VARCHAR(100),
    FOREIGN KEY (assessment_id) REFERENCES admission_assessment(assessment_id),
    FOREIGN KEY (project_id) REFERENCES care_project(project_id)
);

-- 离院主记录
DROP TABLE IF EXISTS discharge_main;
CREATE TABLE discharge_main (
    discharge_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT,
    discharge_reason ENUM('自动出院', '本院死亡', '外院死亡') NOT NULL,
    discharge_date DATE NOT NULL,
    summary_status ENUM('待录入', '草稿', '已提交') NOT NULL,
    handover_status ENUM('待完成', '已完成') NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES patient_info(patient_id)
);

-- 离院小结表
DROP TABLE IF EXISTS discharge_summary;
CREATE TABLE discharge_summary (
    summary_id INT PRIMARY KEY AUTO_INCREMENT,
    discharge_id INT,
    current_illness_id INT,
    summary_type ENUM('普通', '死亡') NOT NULL,
    admission_diagnosis TEXT,
    in_hospital_condition TEXT,
    treatment_process TEXT,
    discharge_condition TEXT,
    discharge_diagnosis TEXT,
    discharge_advice TEXT,
    rescue_process TEXT,
    death_diagnosis TEXT,
    death_cause TEXT,
    FOREIGN KEY (discharge_id) REFERENCES discharge_main(discharge_id),
    FOREIGN KEY (current_illness_id) REFERENCES current_illness(id)
);

-- 离院交接表
DROP TABLE IF EXISTS discharge_handover;
CREATE TABLE discharge_handover (
    handover_id INT PRIMARY KEY AUTO_INCREMENT,
    discharge_id INT,
    item_id INT,
    item_status ENUM('未完成', '已完成') NOT NULL,
    FOREIGN KEY (discharge_id) REFERENCES discharge_main(discharge_id),
    FOREIGN KEY (item_id) REFERENCES handover_item_dict(item_id)
);

-- 医嘱主表
DROP TABLE IF EXISTS medical_order_main;
CREATE TABLE medical_order_main (
    order_id VARCHAR(50) PRIMARY KEY,
    patient_id INT,
    doctor_id INT,
    order_type ENUM('临时', '长期') NOT NULL,
    content TEXT NOT NULL,
    dosage DECIMAL(10,2),
    unit_id INT,
    frequency_id INT,
    send_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    status ENUM('待校对', '已校对', '已执行', '已作废') NOT NULL,
    start_time DATETIME NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES patient_info(patient_id),
    FOREIGN KEY (doctor_id) REFERENCES doctor_info(doctor_id),
    FOREIGN KEY (unit_id) REFERENCES dosage_unit_dict(unit_id),
    FOREIGN KEY (frequency_id) REFERENCES frequency_dict(freq_id)
);

-- 临时医嘱表
DROP TABLE IF EXISTS temporary_order;
CREATE TABLE temporary_order (
    order_id VARCHAR(50) PRIMARY KEY,
    validity_period VARCHAR(50),
    FOREIGN KEY (order_id) REFERENCES medical_order_main(order_id)
);

-- 长期医嘱表
DROP TABLE IF EXISTS long_term_order;
CREATE TABLE long_term_order (
    order_id VARCHAR(50) PRIMARY KEY,
    stop_time DATETIME,
    FOREIGN KEY (order_id) REFERENCES medical_order_main(order_id)
);

-- 医嘱操作记录
DROP TABLE IF EXISTS order_operation_log;
CREATE TABLE order_operation_log (
    log_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id VARCHAR(50),
    type_id INT,
    operation_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    operator_id INT,
    FOREIGN KEY (order_id) REFERENCES medical_order_main(order_id),
    FOREIGN KEY (type_id) REFERENCES operation_type_dict(type_id)
);

-- 处方主表
DROP TABLE IF EXISTS prescription_main;
CREATE TABLE prescription_main (
    prescription_id VARCHAR(50) PRIMARY KEY,
    patient_id INT,
    doctor_id INT,
    prescription_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    prescription_description TEXT,
    status ENUM('未发药', '已发药') NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES patient_info(patient_id),
    FOREIGN KEY (doctor_id) REFERENCES doctor_info(doctor_id)
);

-- 处方明细表
DROP TABLE IF EXISTS prescription_detail;
CREATE TABLE prescription_detail (
    detail_id INT PRIMARY KEY AUTO_INCREMENT,
    prescription_id VARCHAR(50),
    drug_id INT,
    specification VARCHAR(50),
    dosage VARCHAR(50),
    usage VARCHAR(50),
    frequency VARCHAR(50),
    days INT,
    total_quantity VARCHAR(50),
    FOREIGN KEY (prescription_id) REFERENCES prescription_main(prescription_id),
    FOREIGN KEY (drug_id) REFERENCES drug_info(drug_id)
);

-- 处方诊断关联表
DROP TABLE IF EXISTS prescription_diagnosis;
CREATE TABLE prescription_diagnosis (
    prescription_id VARCHAR(50),
    diagnosis_id INT,
    PRIMARY KEY (prescription_id, diagnosis_id),
    FOREIGN KEY (prescription_id) REFERENCES prescription_main(prescription_id),
    FOREIGN KEY (diagnosis_id) REFERENCES diagnosis_dict(diagnosis_id)
);

-- 病历表
DROP TABLE IF EXISTS medical_record;
CREATE TABLE medical_record (
    record_id VARCHAR(50) PRIMARY KEY,
    patient_id INT,
    doctor_id INT,
    visit_date DATETIME NOT NULL,
    chief_complaint TEXT,
    present_illness TEXT,
    past_illness TEXT,
    physical_exam TEXT,
    auxiliary_exam TEXT,
    treatment_opinion TEXT,
    FOREIGN KEY (patient_id) REFERENCES patient_info(patient_id),
    FOREIGN KEY (doctor_id) REFERENCES doctor_info(doctor_id)
);

-- 健康档案主表
DROP TABLE IF EXISTS health_record_main;
CREATE TABLE health_record_main (
    record_id VARCHAR(50) PRIMARY KEY,
    patient_id INT,
    created_doctor_id INT,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME,
    status ENUM('待完善', '已完善', '已归档') NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES patient_info(patient_id),
    FOREIGN KEY (created_doctor_id) REFERENCES doctor_info(doctor_id)
);

-- 入院病史表
DROP TABLE IF EXISTS admission_history;
CREATE TABLE admission_history (
    history_id INT PRIMARY KEY AUTO_INCREMENT,
    record_id VARCHAR(50),
    type_id INT,
    content TEXT,
    record_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (record_id) REFERENCES health_record_main(record_id),
    FOREIGN KEY (type_id) REFERENCES history_type_dict(type_id)
);

-- 诊断与计划表
DROP TABLE IF EXISTS diagnosis_plan;
CREATE TABLE diagnosis_plan (
    plan_id INT PRIMARY KEY AUTO_INCREMENT,
    record_id VARCHAR(50),
    treatment_plan TEXT,
    record_doctor_id INT,
    FOREIGN KEY (record_id) REFERENCES health_record_main(record_id),
    FOREIGN KEY (record_doctor_id) REFERENCES doctor_info(doctor_id)
);

-- 诊断计划明细表
DROP TABLE IF EXISTS diagnosis_plan_detail;
CREATE TABLE diagnosis_plan_detail (
    detail_id INT PRIMARY KEY AUTO_INCREMENT,
    plan_id INT,
    diagnosis_id INT,
    FOREIGN KEY (plan_id) REFERENCES diagnosis_plan(plan_id),
    FOREIGN KEY (diagnosis_id) REFERENCES diagnosis_dict(diagnosis_id)
);