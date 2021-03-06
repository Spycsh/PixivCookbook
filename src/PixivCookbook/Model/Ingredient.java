package PixivCookbook.Model;
/**
 * 
 */


import java.io.Serializable;

/**entity class ingredient
 * @author precision 7710
 * 
 * 
 */
public class Ingredient implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5519338590190678597L;
	private String name="";
	private double num=0.0;
	private String unit="";
	private String preparation="";

	/**
	 * constructor
	 * @param name ingredient name
	 * @param num ingredient amount
	 * @param unit ingredient unit
	 */
	public Ingredient(String name,double num,String unit) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.num=num;
		this.unit=unit;
	}
	/**
	 * constructor
	 * @param name ingredient name
	 * @param num ingredient amount
	 * @param unit ingredient unit
	 * @param preparation preparation time
	 */
	public Ingredient(String name,double num,String unit,String preparation) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.num=num;
		this.unit=unit;
		this.preparation=preparation;
	}
	
	public Ingredient(Ingredient i) {
		// TODO Auto-generated constructor stub
		this.name=i.name;
		this.num=i.num;
		this.unit=i.unit;
		this.preparation=i.preparation;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the num
	 */
	public double getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(double num) {
		this.num = num;
	}
	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}
	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * @return the preparation
	 */
	public String getPreparation() {
		return preparation;
	}
	/**
	 * @param preparation the preparation to set
	 */
	public void setPreparation(String preparation) {
		this.preparation = preparation;
	}
	@Override
	public String toString(){
		return this.num+" "+this.unit+" "+this.preparation+" "+this.name;
	}
}
