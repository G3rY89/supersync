package com.ks.supersync.model.unas.product;

import java.util.List;


import javax.xml.bind.annotation.*;

import com.ks.supersync.model.unas.product.stock.Stocks;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Product")
public class UnasProduct {

    @XmlElement(name = "Action")
    public String action;
    @XmlElement(name = "State")
    public String state;
    @XmlElement(name = "Id")
    public Integer id;
    @XmlElement(name = "Sku") //cikkszám
    public String sku;
    @XmlElement(name = "SkuNew") //új cikkszám (csak setProduct esetén)
    public String newSku;
    @XmlElement(name = "History") //új cikkszám (csak setProduct esetén)
    public List<History> history;
    @XmlElement(name = "Explicit") 
    public Integer explicit;
    @XmlElement(name = "Export") 
    public Export export;
    @XmlElement(name = "PublicInterval") 
    public PublicInterval publicInterval;
    @XmlElement(name = "Name") 
    public String name;
    @XmlElement(name = "Unit") 
    public String unit;
    @XmlElement(name = "MinimumQty") 
    public Integer minimumQty;
    @XmlElement(name = "MaximumQty") 
    public Integer maximumQty;
    @XmlElement(name = "AlertQty") 
    public Integer alertQty;
    @XmlElement(name = "UnitStep")  //check
    public Integer unitStep;
    @XmlElement(name = "AlertUnit")  
    public AlertUnit alertUnit;
    @XmlElement(name = "Weight")  
    public Integer weight;
    @XmlElement(name = "Point")  
    public Integer point;
    @XmlElement(name = "Description")  
    public Description description;
    @XmlElement(name = "Prices")  
    public Prices Prices;
    @XmlElement(name = "Categories")  
    public Categories categories;
    @XmlElement(name = "Url")
    public String url;
    @XmlElement(name = "SefUUrl")
    public String sefUrl;
    @XmlElement(name = "Images")  
    public List<Image> images;
    @XmlElement(name = "Variants")  
    public List<Variant> variants;
    @XmlElement(name = "Datas")  
    public List<Data> datas;
    @XmlElement(name = "Params")  
    public List<Param> params;
    @XmlElement(name = "QtyDiscount")  
    public List<QtyDiscount> qtyDiscount;
    @XmlElement(name = "AdditionalProducts")  
    public List<AdditionalProduct> additionalProducts;
    @XmlElement(name = "SimilarProducts")  
    public List<SimilarProduct> similarProduct;
    @XmlElement(name = "Stocks")  
    public Stocks stocks;
    @XmlElement(name = "PlazaCategory")  
    public PlazaCategory plazaCategory;
    @XmlElement(name = "OnlineContent")  
    public OnlineContent onlineContent;
    @XmlElement(name = "Types")  
    public Type types;
    @XmlElement(name = "Meta")  
    public Meta meta;

    public UnasProduct(){
        this.stocks = new Stocks();
        this.categories = new Categories();
    }

}