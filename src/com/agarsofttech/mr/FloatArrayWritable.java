package com.agarsofttech.mr;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.Writable;


public abstract class FloatArrayWritable implements Writable {
  private float[][] array;
  private String[] keyarray;
  private int length = 0;

  public FloatArrayWritable() {
  }

  /**
   * Constructor with array as input.
   *
   * @param array input array
   */
  public FloatArrayWritable(float[][] array) {
    this.array = array;
    this.length = array.length;
  }

  /**
   * Constructor with array as input and with a specific size.
   *
   * @param array input array
   * @param length length
   */
  public FloatArrayWritable(float[][] array, int length) {
    this.array = array;
    this.length = length;
  }

  /**
   * Constructor that creates an empty array of a particular size.
   *
   * @param size array size
   */
  public FloatArrayWritable(int size) {
    array = new float[size][2];
  }

  @Override
  public void readFields(DataInput in) throws IOException {
    this.length = in.readInt();
    array = new float[length][2];
    for (int i = 0; i < length; i++) {
      array[i][1] = in.readFloat();
    }
  }

  @Override
  public void write(DataOutput out) throws IOException {
    out.writeInt(length);
    for (int i = 0; i < length; i++) {
      out.writeFloat(array[i][1]);
    }
  }

  /**
   * Returns a deep copy of the array. The length of the returned array will always be the value
   * {@link #size()}. That is, trailing unused space in the underlying array will be trimmed.
   */
  public float[] getClone() {
    float[] copy = new float[length];
    System.arraycopy(array, 0, copy, 0, length);
    return copy;
  }

  /**
   * Returns a reference to the underlying array. Note that the underlying array may have length
   * longer than the value of {@link #size()}.
   */
  public float[][] getArray() {
    return array;
  }

  /**
   * Sets the underlying array.
   */
  public void setArray(float[][] array) {
    if (array == null) {
      this.array = new float[0][0];
      this.length = 0;
      return;
    }

    this.array = array;
    this.length = array.length;
  }

  /**
   * Sets the underlying array and a specified length.
   */
  public void setArray(float[][] array, int length) {
    this.array = array;
    this.length = length;
  }

  /**
   * Returns the value at index <i>i</i>. Note that no bounds checking is performed.
   *
   * @param i index position
   */
  public String getkey(int i) {
    return keyarray[i];
  }
  public float getvalue(int i) {
	    return array[i][1];
	  }
  /**
   * Sets the value at index <i>i</i>.
   *
   * @param i position in array
   * @param v value
   */
  public void set(int i, String k, float v) {
    array[i][0] = i;
    keyarray[i] = k;
    array[i][1] = v;
  }

  /**
   * Returns the size of the float array.
   */
  public int size() {
    return length;
  }

  @Override
  public String toString() {
    return Arrays.toString(array);
  }
}