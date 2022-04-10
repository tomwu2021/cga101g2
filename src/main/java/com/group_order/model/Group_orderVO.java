package com.group_order.model;

import java.sql.Timestamp;

import javax.tools.JavaCompiler;

public class Group_orderVO implements java.io.Serializable {
 private Integer group_order_id;
 private Integer product_id;
 private Timestamp create_time;
 private Timestamp end_time;
 private Integer end_type;
 private Integer final_price;
 private Integer status;

 public Group_orderVO() {
 }

 public Integer getGroup_order_id() {
  return group_order_id;
 }

 public void setGroup_order_id(Integer group_order_id) {
  this.group_order_id = group_order_id;
 }

 public Integer getProduct_id() {
  return product_id;
 }

 public void setProduct_id(Integer product_id) {
  this.product_id = product_id;
 }

 public Timestamp getCreate_time() {
  return create_time;
 }

 public void setCreate_time(Timestamp create_time) {
  this.create_time = create_time;
 }

 public Timestamp getEnd_time() {
  return end_time;
 }

 public void setEnd_time(Timestamp end_time) {
  this.end_time = end_time;
 }

 public Integer getEnd_type() {
  return end_type;
 }

 public void setEnd_type(Integer end_type) {
  this.end_type = end_type;
 }

 public Integer getFinal_price() {
  return final_price;
 }

 public void setFinal_price(Integer final_price) {
  this.final_price = final_price;
 }

 public Integer getStatus() {
  return status;
 }

 public void setStatus(Integer status) {
  this.status = status;
 }

}