Feature: Order Fulfillment From Ready to Pick to Delivered
  Scenario: Trigger the store engagement
  Given I have a valid Order Number which is ready to pick
  When I will login to the store application for that ship node
  And By entering order number I will select the shipment to fulfill
  Then After validating the Item Id I will do Pick all shipments and finish picking shipment
  Then Order line should move to "RTS"
  When I do Continue Packing by updating carrier and volumetric
  And Finish Pack packing
  Then Order line should move to "Packed"
  And After generating AWB I will do print invoice
  Then Invoice should be generated
  Then Order line should move to "Connect To Carrier"
  When I click on View All Shipment Button and Filter by providing Shipment Details
  Then Order should be displayed in Shipment summary page
  When I click on Handover to Shipment Button
  Then Order line should move to "HOTC"
  When i Select Delivery in Progress Button
  Then Order line should move to "DIP"
  When i Select Out for Delivery Button
  Then Order line should move to "Out for Delivery"
  When i select Delivered Button
  Then Order line should move to "Delivered"
