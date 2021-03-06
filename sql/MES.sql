/*
 Navicat Premium Data Transfer

 Source Server         : mes
 Source Server Type    : Oracle
 Source Server Version : 110200
 Source Schema         : MES

 Target Server Type    : Oracle
 Target Server Version : 110200
 File Encoding         : 65001

 Date: 12/10/2021 01:55:56
*/


-- ----------------------------
-- Table structure for EQUIPMENT_MASTER
-- ----------------------------
DROP TABLE "MES"."EQUIPMENT_MASTER";
CREATE TABLE "MES"."EQUIPMENT_MASTER" (
  "COMP_CD" NUMBER(10,0),
  "PLANT_CD" NUMBER(10,0),
  "LINE_CD" VARCHAR2(30 BYTE),
  "EQUIP_CD" VARCHAR2(30 BYTE),
  "INDEX_CD" NUMBER(10,0),
  "CONTENTS" VARCHAR2(30 BYTE),
  "GRADE" VARCHAR2(30 BYTE),
  "USE_YN" VARCHAR2(30 BYTE),
  "IN_USR_ID" VARCHAR2(30 BYTE),
  "IN_DATE" DATE,
  "UP_USR_ID" VARCHAR2(30 BYTE),
  "UP_DATE" DATE
)
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for FACTORY
-- ----------------------------
DROP TABLE "MES"."FACTORY";
CREATE TABLE "MES"."FACTORY" (
  "COMP_CD" NUMBER(10,0),
  "PLANT_CD" NUMBER(10,0),
  "PLANT_NM" VARCHAR2(30 BYTE),
  "VALID_FR_DT" DATE,
  "VALID_TO_DT" DATE,
  "REMARK" VARCHAR2(30 BYTE),
  "IN_USR_ID" VARCHAR2(30 BYTE),
  "IN_DATE" DATE,
  "UP_USR_ID" VARCHAR2(30 BYTE),
  "UP_DATE" DATE
)
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for ITEM
-- ----------------------------
DROP TABLE "MES"."ITEM";
CREATE TABLE "MES"."ITEM" (
  "COMP_CD" NUMBER(10,0) NOT NULL,
  "PLANT_CD" NUMBER(10,0) NOT NULL,
  "ACCT_ID" VARCHAR2(10 BYTE) NOT NULL,
  "ITEM_CD" NUMBER(10,0) NOT NULL,
  "ITEM_NM" VARCHAR2(20 BYTE),
  "ITEM_SPEC" VARCHAR2(50 BYTE),
  "ITEM_SPEC2" VARCHAR2(50 BYTE),
  "ITEM_COLOR" VARCHAR2(10 BYTE),
  "CUST_CD" VARCHAR2(20 BYTE),
  "ACCT_PRICE" NUMBER(10,0),
  "CURRENCY" VARCHAR2(10 BYTE),
  "UNIT_CD" VARCHAR2(10 BYTE),
  "REMARK" VARCHAR2(255 BYTE),
  "IN_USR_ID" VARCHAR2(10 BYTE),
  "IN_DATE" DATE,
  "UP_USR_ID" VARCHAR2(10 BYTE),
  "UP_DATE" DATE
)
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for LINE
-- ----------------------------
DROP TABLE "MES"."LINE";
CREATE TABLE "MES"."LINE" (
  "COMP_CD" NUMBER(20,0) NOT NULL,
  "PLANT_CD" NUMBER(20,0) NOT NULL,
  "LINE_CD" VARCHAR2(50 BYTE) NOT NULL,
  "LINE_NM" VARCHAR2(50 BYTE) NOT NULL,
  "USE_YN" CHAR(1 BYTE) NOT NULL,
  "REMARK" VARCHAR2(50 BYTE),
  "IN_USR_ID" VARCHAR2(50 BYTE),
  "IN_DATE" DATE NOT NULL,
  "UP_USR_ID" VARCHAR2(50 BYTE),
  "UP_DATE" DATE
)
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for MAINBOARD
-- ----------------------------
DROP TABLE "MES"."MAINBOARD";
CREATE TABLE "MES"."MAINBOARD" (
  "BOARDNUM" NUMBER NOT NULL,
  "BOARDTITLE" VARCHAR2(50 BYTE) NOT NULL,
  "BOARDCONTENT" VARCHAR2(3000 BYTE) NOT NULL,
  "MEMBERID" VARCHAR2(50 BYTE) NOT NULL,
  "BOARDDATE" DATE NOT NULL,
  "BOARDFILE" VARCHAR2(500 BYTE),
  "BOARDAVAILABLE" NUMBER,
  "BOARDREADCOUNT" NUMBER DEFAULT 0
)
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for MEMBER
-- ----------------------------
DROP TABLE "MES"."MEMBER";
CREATE TABLE "MES"."MEMBER" (
  "MEMBERID" VARCHAR2(50 BYTE) NOT NULL,
  "NAME" VARCHAR2(50 BYTE),
  "PASSWORD" VARCHAR2(10 BYTE),
  "REGDATE" DATE
)
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for ORDERING
-- ----------------------------
DROP TABLE "MES"."ORDERING";
CREATE TABLE "MES"."ORDERING" (
  "COMP_CD" NUMBER(10,0) NOT NULL,
  "PLANT_CD" NUMBER(10,0) NOT NULL,
  "ORDER_NO" VARCHAR2(20 BYTE) NOT NULL,
  "ORDER_DT" DATE,
  "ITEM_CD" NUMBER(10,0),
  "BARCODE" VARCHAR2(50 BYTE),
  "MANU_NO" VARCHAR2(50 BYTE),
  "DELIVERY_DT" DATE NOT NULL,
  "ORDER_QTY" NUMBER(10,0),
  "ORDER_STATUS" VARCHAR2(10 BYTE),
  "REMARK" VARCHAR2(255 BYTE),
  "FINISHED_ID" VARCHAR2(30 BYTE),
  "FINISHED_DT" DATE
)
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for PRODUCTION
-- ----------------------------
DROP TABLE "MES"."PRODUCTION";
CREATE TABLE "MES"."PRODUCTION" (
  "COMP_CD" NUMBER(10,0) NOT NULL,
  "PLANT_CD" NUMBER(10,0) NOT NULL,
  "ORDER_NO" VARCHAR2(30 BYTE),
  "WO_NO" VARCHAR2(30 BYTE) NOT NULL,
  "LINE_CD" VARCHAR2(30 BYTE),
  "EQUIP_CD" VARCHAR2(10 BYTE),
  "START_DT" DATE,
  "START_SHIFT" VARCHAR2(10 BYTE),
  "END_DT" DATE,
  "END_SHIFT" VARCHAR2(10 BYTE),
  "FLAG_END" VARCHAR2(10 BYTE),
  "PLAN_QTY" NUMBER(10,0),
  "REMARK" VARCHAR2(255 BYTE)
)
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for PRODUCTION_LOG
-- ----------------------------
DROP TABLE "MES"."PRODUCTION_LOG";
CREATE TABLE "MES"."PRODUCTION_LOG" (
  "COMP_CD" NUMBER(10,0) NOT NULL,
  "PLANT_CD" NUMBER(10,0) NOT NULL,
  "WO_NO" VARCHAR2(30 BYTE) NOT NULL,
  "WO_SEQ" NUMBER(30,0) NOT NULL,
  "LINE_CD" VARCHAR2(10 BYTE),
  "EQUIP_CD" VARCHAR2(10 BYTE),
  "WORKER" VARCHAR2(10 BYTE),
  "START_DT" DATE,
  "END_DT" DATE,
  "IN_QTY" NUMBER(20,0),
  "OUT_QTY" NUMBER(20,0),
  "NG_QTY" NUMBER(10,0),
  "REMARK" VARCHAR2(255 BYTE)
)
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Sequence structure for IDX_ORDER_NO
-- ----------------------------
DROP SEQUENCE "MES"."IDX_ORDER_NO";
CREATE SEQUENCE "MES"."IDX_ORDER_NO" MINVALUE 1 MAXVALUE 200 INCREMENT BY 1 CYCLE CACHE 20;

-- ----------------------------
-- Sequence structure for IDX_WO_NO
-- ----------------------------
DROP SEQUENCE "MES"."IDX_WO_NO";
CREATE SEQUENCE "MES"."IDX_WO_NO" MINVALUE 1 MAXVALUE 200 INCREMENT BY 1 CYCLE CACHE 20;

-- ----------------------------
-- Primary Key structure for table ITEM
-- ----------------------------
ALTER TABLE "MES"."ITEM" ADD CONSTRAINT "SYS_C007954" PRIMARY KEY ("COMP_CD", "PLANT_CD", "ITEM_CD");

-- ----------------------------
-- Checks structure for table ITEM
-- ----------------------------
ALTER TABLE "MES"."ITEM" ADD CONSTRAINT "SYS_C007951" CHECK ("COMP_CD" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "MES"."ITEM" ADD CONSTRAINT "SYS_C007952" CHECK ("PLANT_CD" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "MES"."ITEM" ADD CONSTRAINT "SYS_C007953" CHECK ("ACCT_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table LINE
-- ----------------------------
ALTER TABLE "MES"."LINE" ADD CONSTRAINT "TEST_2" PRIMARY KEY ("COMP_CD", "PLANT_CD", "LINE_CD");

-- ----------------------------
-- Checks structure for table LINE
-- ----------------------------
ALTER TABLE "MES"."LINE" ADD CONSTRAINT "SYS_C007899" CHECK ("LINE_NM" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "MES"."LINE" ADD CONSTRAINT "SYS_C007900" CHECK ("USE_YN" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "MES"."LINE" ADD CONSTRAINT "SYS_C007901" CHECK ("IN_DATE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table MAINBOARD
-- ----------------------------
ALTER TABLE "MES"."MAINBOARD" ADD CONSTRAINT "SYS_C007996" PRIMARY KEY ("BOARDNUM");

-- ----------------------------
-- Checks structure for table MAINBOARD
-- ----------------------------
ALTER TABLE "MES"."MAINBOARD" ADD CONSTRAINT "SYS_C007992" CHECK ("BOARDTITLE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "MES"."MAINBOARD" ADD CONSTRAINT "SYS_C007993" CHECK ("BOARDCONTENT" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "MES"."MAINBOARD" ADD CONSTRAINT "SYS_C007994" CHECK ("MEMBERID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "MES"."MAINBOARD" ADD CONSTRAINT "SYS_C007995" CHECK ("BOARDDATE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table MEMBER
-- ----------------------------
ALTER TABLE "MES"."MEMBER" ADD CONSTRAINT "PK_DEPT" PRIMARY KEY ("MEMBERID");

-- ----------------------------
-- Primary Key structure for table ORDERING
-- ----------------------------
ALTER TABLE "MES"."ORDERING" ADD CONSTRAINT "SYS_C007813" PRIMARY KEY ("COMP_CD", "PLANT_CD", "ORDER_NO");

-- ----------------------------
-- Checks structure for table ORDERING
-- ----------------------------
ALTER TABLE "MES"."ORDERING" ADD CONSTRAINT "SYS_C007809" CHECK ("COMP_CD" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "MES"."ORDERING" ADD CONSTRAINT "SYS_C007810" CHECK ("PLANT_CD" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "MES"."ORDERING" ADD CONSTRAINT "SYS_C007811" CHECK ("ORDER_NO" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "MES"."ORDERING" ADD CONSTRAINT "SYS_C007812" CHECK ("DELIVERY_DT" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table PRODUCTION
-- ----------------------------
ALTER TABLE "MES"."PRODUCTION" ADD CONSTRAINT "SYS_C008007" PRIMARY KEY ("COMP_CD", "PLANT_CD", "WO_NO");

-- ----------------------------
-- Primary Key structure for table PRODUCTION_LOG
-- ----------------------------
ALTER TABLE "MES"."PRODUCTION_LOG" ADD CONSTRAINT "SYS_C008008" PRIMARY KEY ("COMP_CD", "PLANT_CD", "WO_NO", "WO_SEQ");
