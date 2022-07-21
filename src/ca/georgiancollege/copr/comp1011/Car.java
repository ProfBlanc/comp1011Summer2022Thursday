package ca.georgiancollege.copr.comp1011;

import com.google.gson.annotations.Expose;

public class Car implements Comparable<Car> {

// @Expose(serialize = true, deserialize = true)
 private String make, model;

// @Expose(serialize = false, deserialize = false)
 private transient int year;

 public Car(String make, String model, int year) {
  this.make = make;
  this.model = model;
  this.year = year;
 }

 public String getMake() {
  return make;
 }

 public void setMake(String make) {
  this.make = make;
 }

 public String getModel() {
  return model;
 }

 public void setModel(String model) {
  this.model = model;
 }

 public int getYear() {
  return year;
 }

 public void setYear(int year) {
  this.year = year;
 }

 public String toString() {
  return String.format("%d %s-%s", year, make, model);
 }

 @Override
 public int compareTo(Car otherCar) {

  if (this.year != otherCar.getYear())
   return this.getYear() - otherCar.getYear();
  else {
   String thisCar = make + model;
   String other = otherCar.getMake() + otherCar.getModel();
   return thisCar.compareTo(other);
  }
 }
}