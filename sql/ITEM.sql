/*
 Navicat Premium Data Transfer

 Source Server         : mes
 Source Server Type    : Oracle
 Source Server Version : 110200
 Source Schema         : MES

 Target Server Type    : Oracle
 Target Server Version : 110200
 File Encoding         : 65001

 Date: 04/10/2021 00:09:20
*/


-- ----------------------------
-- Table structure for ITEM
-- ----------------------------
DROP TABLE "MES"."ITEM";
CREATE TABLE "MES"."ITEM" (
  "COMP_CD" NUMBER(10,0) NOT NULL,
  "PLANT_CD" NUMBER(10,0) NOT NULL,
  "ACCT_ID" VARCHAR2(10 BYTE) NOT NULL,
  "ITME_CD" NUMBER(10,0) NOT NULL,
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
-- Primary Key structure for table ITEM
-- ----------------------------
ALTER TABLE "MES"."ITEM" ADD CONSTRAINT "SYS_C007954" PRIMARY KEY ("COMP_CD", "PLANT_CD", "ITME_CD");

-- ----------------------------
-- Checks structure for table ITEM
-- ----------------------------
ALTER TABLE "MES"."ITEM" ADD CONSTRAINT "SYS_C007951" CHECK ("COMP_CD" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "MES"."ITEM" ADD CONSTRAINT "SYS_C007952" CHECK ("PLANT_CD" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "MES"."ITEM" ADD CONSTRAINT "SYS_C007953" CHECK ("ACCT_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;