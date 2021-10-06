/*
 Navicat Premium Data Transfer

 Source Server         : mes
 Source Server Type    : Oracle
 Source Server Version : 110200
 Source Schema         : MES

 Target Server Type    : Oracle
 Target Server Version : 110200
 File Encoding         : 65001

 Date: 06/10/2021 17:19:21
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
  FREELISTS 1
  FREELIST GROUPS 1
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
