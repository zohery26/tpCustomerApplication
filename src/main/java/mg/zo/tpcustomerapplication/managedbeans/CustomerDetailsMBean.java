/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.zo.tpcustomerapplication.managedbeans;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import mg.zo.tpcustomerapplication.entities.Customer;
import mg.zo.tpcustomerapplication.entities.DiscountCode;
import mg.zo.tpcustomerapplication.session.CustomerManager;
import mg.zo.tpcustomerapplication.session.DiscountCodeManager;

/**
 *
 * @author macbookair
 */
@Named(value = "customerDetailsMBean")
@ViewScoped
public class CustomerDetailsMBean implements Serializable {
  private int idCustomer;
  private Customer customer;
  
  @EJB
  private DiscountCodeManager discountCodeManager;

  @EJB
  private CustomerManager customerManager;
  
  

  public int getIdCustomer() {
    return idCustomer;
  }

  public void setIdCustomer(int idCustomer) {
    this.idCustomer = idCustomer;
  }

  /**
   * Retourne les détails du client courant (celui dans l'attribut customer de
   * cette classe), qu'on appelle une propriété (property)
   */
    public Customer getDetails() {
      return customer;
    }

  /**
   * Action handler - met à jour dans la base de données les données du client
   * contenu dans la variable d'instance customer.
   * @return la prochaine page à afficher, celle qui affiche la liste des clients.
   */
  public String update() {
    // Modifie la base de données.
    // Il faut affecter à customer.
    customer = customerManager.update(customer);
    return "CustomerList";
  }

  public void loadCustomer() {
    this.customer = customerManager.getCustomer(idCustomer);
  }
  
  /**
   * Retourne la liste de tous les DiscountCode.
   */
  public List<DiscountCode> getDiscountCodes() {
    return discountCodeManager.getAllDiscountCodes();
  }
  
  /**
   * getter pour la propriété discountCodeConverter.
   */              
  public Converter<DiscountCode> getDiscountCodeConverter() {
    return new Converter<DiscountCode>() {
      /**
       * Convertit une String en DiscountCode.
       *
       * @param value valeur à convertir
       */
      @Override
      public DiscountCode getAsObject(FacesContext context, UIComponent component, String value) {
        return discountCodeManager.findById(value);
      }

      /**
       * Convertit un DiscountCode en String.
       *
       * @param value valeur à convertir
       */
      @Override
      public String getAsString(FacesContext context, UIComponent component, DiscountCode value) {
        return value.getDiscountCode();
      }
    };
  }
}