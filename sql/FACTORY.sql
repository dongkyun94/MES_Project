/*
 Navicat Premium Data Transfer

 Source Server         : mes
 Source Server Type    : Oracle
 Source Server Version : 110200
 Source Host           : localhost:1521
 Source Schema         : MES

 Target Server Type    : Oracle
 Target Server Version : 110200
 File Encoding         : 65001

 Date: 30/09/2021 18:03:28
*/


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
  FREELISTS 1
  FREELIST GROUPS 1
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
