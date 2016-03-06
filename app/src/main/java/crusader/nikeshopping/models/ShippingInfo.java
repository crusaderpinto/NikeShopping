package crusader.nikeshopping.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ShippingInfo {

@SerializedName("shippingServiceCost")
@Expose
private List<ShippingServiceCost> shippingServiceCost = new ArrayList<ShippingServiceCost>();
@SerializedName("shippingType")
@Expose
private List<String> shippingType = new ArrayList<String>();
@SerializedName("shipToLocations")
@Expose
private List<String> shipToLocations = new ArrayList<String>();

/**
* 
* @return
* The shippingServiceCost
*/
public List<ShippingServiceCost> getShippingServiceCost() {
return shippingServiceCost;
}

/**
* 
* @param shippingServiceCost
* The shippingServiceCost
*/
public void setShippingServiceCost(List<ShippingServiceCost> shippingServiceCost) {
this.shippingServiceCost = shippingServiceCost;
}

/**
* 
* @return
* The shippingType
*/
public List<String> getShippingType() {
return shippingType;
}

/**
* 
* @param shippingType
* The shippingType
*/
public void setShippingType(List<String> shippingType) {
this.shippingType = shippingType;
}

/**
* 
* @return
* The shipToLocations
*/
public List<String> getShipToLocations() {
return shipToLocations;
}

/**
* 
* @param shipToLocations
* The shipToLocations
*/
public void setShipToLocations(List<String> shipToLocations) {
this.shipToLocations = shipToLocations;
}

}
