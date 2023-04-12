# Online-Shopping-Cart-System

Scope:   To sell the products online using Visa and Master card for payment.  Sell different varieties of products from different vendors maintained by the vendors.  A reports generation module must be available for management and vendors to see the status of the sales online in terms of products and in terms of Dollar value.  The Online system administrator has all the rights to create and manage the users online in terms of Administrator, Registered users, Guest Users, Vendors, and Clients (Requirements givers and receivers).  The system must be a role base system with one of the windows authorizing feature (AD).  All the data must be available in the database with highly secured process.  Vendors will be maintaining their products online using the account given by shopping cart system.  Administrator has to approve all the products before appearing on the shopping cart system.  First time vendor when registers, he has to pay $20,000 as one time payment.  The profits are shared 80% (Vendors) and 20% (Shopping cart system).  An SMTP module must be provided to generate auto emails to the purchasing users, Delivering vendors.
Scope Details:   The system should allow the users to make use of the following modules based on their level of authorization.
1.	User management module
1.1. Register, Update and Remove User
	1.1.1. End User: These are the normal users who will visit the website and purchase items.
	1.1.2. Vender User: These are the users who will create account making the payment of $20,000 and will be able to list
           the product for the sell.

1.2. Approve Vender: Admin of the system can approve the vender based on the information provided.

2.	End User Module:
2.1. Adding Items to cart
2.2. Updating the cart
2.4. Checkout process



3.	Shopping Cart Module:
3.1.	Add to cart: User can add items to the cart. 
3.2.	Checkout for the cart items: User can checkout from the cart with cart items.
3.3.	Order tracking: User can track items which he/s has purchase.
3.4.	Cart Management: User can manage the cart like can remove the items from cart, modify quantities.  


4.	Product Module:
4.1.	Vendor can enable CRUD operation on their own products and admin can on all vendors’ products.
4.2.	User can view all the products.
4.3.	Product Search and filtering.
4.4.	Report (Monthly sale by vendor)
4.5.	Product review 

5.	Payment Module:
5.1.	Integrate the payment gateway providers
5.2.	Payment authorization, capture, and refunds
5.3.	Calculate tax
5.4.	Split payment made by the user into three parts 80% to the vendor, 20% to the e-commerce owner and 7% tax to the IRS. (Optional)
5.5.	Payment method support
5.5.1 Visa
5.5.2 Master card
      5.6.     Secure payment processing (encrypting sensitive payment data such as credit card numbers and CVV codes)

6.	Authentication and Authorization Module:
6.1.	Implement Spring Security or OAuth2
