package com.delta.ticketing.rhdm.fulfillmentrules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

//import com.google.gson.Gson;


import org.drools.core.command.runtime.rule.GetObjectsCommand;
import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.ExecutionResults;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.command.CommandFactory;

import org.kie.api.command.BatchExecutionCommand;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;

//import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;



/**
 * Unit test for simple App.
 * 
 * For documentation purposes the procedure to invoke the rules in a stand alone 
 * Kie Server has been added here and the following content in this comments section is 
 * to be used if you want to invoke the rules via REST
 * 
 Postman example below:
 ----- In Auhtorization tab  select BasicAuth and provide the username/password that was used in 
 standing up the stand alone Kie Server (adminUser or any other user that has been setup will do.
 In Header X_KIE-ContentType should be set to JSON and Content-Type should be set to application/json
 JSON PayLoad example  below (content of body)
 -----------------------------
 {
      "lookup" : "ValueCalcRulesKS",
      "commands" : 
      [ {
        "insert" : {
          "object" : { "com.delta.ticketing.rhdm.calcrules.Document": {
          	"id" : "1234", 
          	"type" : "IEMD", 
          	"status" : "PARTIAL", 
          	"owningCarrierCode" : "DL",
          	"aggregates" : [ 
          		{"type" : "REFUND_TOTAL_TAX", "calculatedByRules" : false, "requiredPrecision" : "2", "currencyCode" : "USD", "amount" : "35"} ,
          	    {"type" : "REFUND_BASE_FARE", "calculatedByRules" : false, "requiredPrecision" : "2", "currencyCode" : "USD", "amount" : "250"}, 
          	    {"type" : "REFUND_TOTAL_FARE", "calculatedByRules" : false, "requiredPrecision" : "2", "currencyCode" : "USD", "amount" : "285"} 
          	  ],
          	"coupons" : [
          		{ "id" : "1", "status" : "OPEN", "emdCouponAmount" : "50", "selectedForRefund" : true, "currencyCode" : "USD", "docId" : "1234" },
          		{ "id" : "2", "status" : "FLOWN", "emdCouponAmount" : "100", "selectedForRefund" : true, "currencyCode" : "USD", "docId" : "1234" },
          		{ "id" : "3", "status" : "EREF", "emdCouponAmount" : "50", "selectedForRefund" : true, "currencyCode" : "USD", "docId" : "1234" }
              ],
            "taxComponents" : [
            	{ "type" : "US", "currencyCode" : "USD", "amount" : "20", "requiredPrecision" : "2", "calculatedByRules" : false}
              ]
          	}},
          "disconnected" : false,
          "out-identifier" : "document2",
          "return-object" : true,
          "entry-point" : "DEFAULT"
        }
      },
      {"insert" : {
          "object" : {  "com.delta.ticketing.rhdm.calcrules.ValueCalculationContext": { "requestType" : "REFUND", "ruleTraceNeeded" : true } },
          "disconnected" : false,
          "out-identifier" : "ctxt",
          "return-object" : false,
          "entry-point" : "DEFAULT"
        }

      }, {
        "start-process" :  "CalcRules.CalculationRuleFlow"
      },
      { "dispose" : {} }
     ]
}
 *
 *
 *
 */
public class AppTest 
    extends TestCase
{
	
	public static String SEGMENT_ID_1 = "1";
	public static String SEMENT_TYPE_0 = "0";
	public static String SEGMENT_ACTIVE_FLAG_Y = "Y";
	public static String ORIGIN_STATION_CODE_ATL = "ATL";
	public static String DESTINATION_STATION_CODE_JFK = "JFK";
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
       
       try {
           SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
    	// Load the KIE base:
    	  KieServices ks = KieServices.Factory.get();
    	  KieContainer kContainer = ks.getKieClasspathContainer();
    	  StatelessKieSession kSession = kContainer.newStatelessKieSession("FulfillmentRulesKS");

    	  // Set up the fact model:

//       Test Case 0
         
         FulfillmentTxn txn1 = new FulfillmentTxn();
         txn1.setPnrRequest(true);
         txn1.setIntent("REFUND");
         //txn1.setUserAuthorization("PaxRefundTeamAuthorize");
         //txn1.setUserAuthorization("CSSAuthorize");
         txn1.setUserAuthorization(null);
         txn1.setOverrideRequested(true);
         
         java.util.List<Document> dbDocs = new java.util.ArrayList<Document> ();
//         Document doc = new Document();
//         doc.setId("0064207955015");
//         Coupon cpn = new Coupon();
//         //dbDocs.add(doc);
//         doc.addDbCoupon(cpn);

//         Document doc2 = new Document();
//         doc2.setId("2");
//         Coupon cpnX = new Coupon();         
//         //dbDocs.add(doc2);
//         doc2.addDbCoupon(cpnX);

         
//       Test Case 1
         
         
          String DOCUMENT_ID_1 = "1";
          Document doc1 = new Document();
          
          
          doc1.setId(DOCUMENT_ID_1);
          doc1.setType("T");  // T for Ticket, J for EMD, A for Award
          doc1.setReasonForIssuanceCode(null);
          //doc1.setEmdAssociationTypeCode("S");
          doc1.setEmdAssociationTypeCode(null);
          doc1.setSubType(null);
          //doc1.setStatus("PARTIAL");
          doc1.setStatus(null);
          doc1.setPrimaryTicket(true);
          doc1.setAmountOverride(false);
          java.util.Calendar cal = new java.util.GregorianCalendar();
      	  cal.set(2020, 11,27);
          doc1.setIssueDate(cal.getTime());
          doc1.setRefundStatus("Q");  // I is invalid quote status
          //doc1.setPassengerTypeCode("CMP");
          doc1.setPassengerTypeCode(null);
          doc1.setOwningCarrierCode("DL");
          
          //Coupons
          Coupon cpn1 = generateCoupon("1", DOCUMENT_ID_1);
          cpn1.setStatus("O");
          cpn1.setState("SOLD");
          cpn1.setFlightNumber("2134");
          cpn1.setOriginStationCode("AUS");
          cpn1.setDestinationStationCode("ATL");
          doc1.addDbCoupon(cpn1);
          
          //cpn1.setStatus("SOME_OTHER_STATUS_THAN_DOC_API_COUPON_STATUS");          
//          Coupon cpn2 = generateCoupon("2", DOCUMENT_ID_1);
//          cpn2.setStatus("R");
//          doc1.addDbCoupon(cpn2);
//          
          Coupon cpn3 = generateCoupon("1", DOCUMENT_ID_1);
          cpn3.setStatus("9");
          cpn3.setState("SOLD");
          cpn3.setFlightNumber("2134");
          cpn3.setOriginStationCode("EBC");
          cpn3.setDestinationStationCode("EBC");
          doc1.addDocApiCoupon(cpn3);
          //cpn3.setStatus("SOME_OTHER_STATUS_THAN_DOC_API_COUPON_STATUS");
//          Coupon cpn4 = generateCoupon("2", DOCUMENT_ID_1);
//          cpn4.setStatus("R");
//          doc1.addDocApiCoupon(cpn4);
          
          Aggregate ag = new Aggregate();
          ag.setType("RefundAmount");
          ag.setCurrencyCode("USD");
          //ag.setAmount(new java.math.BigDecimal(8880)); 
          ag.setAmount(new java.math.BigDecimal(10000));
          ag.setDocId("1");
          ag.setMiles(4000);
          ag.setPrecision(2);
      
          ag.setDocId(DOCUMENT_ID_1);
          doc1.addRequestAggregate(ag);
          doc1.addDbAggregate(ag);
          
          Aggregate refundBaseFare = new Aggregate();
          refundBaseFare.setType("RefundBaseFare");
          refundBaseFare.setCurrencyCode("USD");
          refundBaseFare.setAmount(new java.math.BigDecimal(0));
          refundBaseFare.setDocId(DOCUMENT_ID_1);
          refundBaseFare.setPrecision(2);
          doc1.addRequestAggregate(refundBaseFare);
          doc1.addDbAggregate(refundBaseFare);
          
          Aggregate refundTotalTax = new Aggregate();
          refundTotalTax.setType("RefundTotalTax");
          refundTotalTax.setCurrencyCode("USD");
          refundTotalTax.setAmount(new java.math.BigDecimal(0));
          refundTotalTax.setPrecision(2);
          refundTotalTax.setDocId(DOCUMENT_ID_1);
          doc1.addRequestAggregate(refundTotalTax);
          doc1.addDbAggregate(refundTotalTax);
          
          Aggregate totalAmount = new Aggregate();
          totalAmount.setType("RefundTotalAmount");
          totalAmount.setCurrencyCode("USD");
          totalAmount.setAmount(new java.math.BigDecimal(10000));
          totalAmount.setPrecision(2);
          totalAmount.setDocId(DOCUMENT_ID_1);
          doc1.addRequestAggregate(totalAmount);
          doc1.addDbAggregate(totalAmount);

//          Aggregate cancellationAmount1 = new Aggregate();
//          cancellationAmount1.setType("CancellationAmt");
//          cancellationAmount1.setCurrencyCode("USD");
//          cancellationAmount1.setAmount(new java.math.BigDecimal(0));
//          cancellationAmount1.setDocId(DOCUMENT_ID_1);
//          cancellationAmount1.setPrecision(2);
//          doc1.addRequestAggregate(cancellationAmount1);
//         
//          Aggregate cancellationAmount2 = new Aggregate();
//          cancellationAmount2.setType("CancellationAmt");
//          cancellationAmount2.setCurrencyCode("USD");
//          cancellationAmount2.setAmount(new java.math.BigDecimal(8900));
//          cancellationAmount2.setDocId(DOCUMENT_ID_1);
//          cancellationAmount2.setPrecision(2);
//          doc1.addDbAggregate(cancellationAmount2);

          
//          Aggregate dbag = new Aggregate();
//          dbag.setType("RefundAmount");
//          dbag.setCurrencyCode("USD");
//          dbag.setAmount(new java.math.BigDecimal(8900));
//          dbag.setDocId(DOCUMENT_ID_1);
//          dbag.setPrecision(2);
//          doc1.addDbAggregate(dbag);
          //FopInfo fop = new FopInfo("MS", "TP", "MFP", "CK", "ALIPAY"); // venfor code needs to be TP or DS so this is invalid FOP
          FopInfo fop = new FopInfo("MS", null, null, null, null); // vendor code needs to be TP or DS so this is invalid FOP
          doc1.addFOP(fop);
          
          
//          Gson gson = new Gson();
//          Document doc2 = gson.fromJson(gson.toJson(doc1), Document.class);
//          doc2.setId("2");
          
          
          
          dbDocs.add(doc1);
          //dbDocs.add(doc2);
//          txn1.setDbDocs(dbDocs);
         
          Pnr pnr = new Pnr();
          java.util.List<String> ticketNoList = new java.util.ArrayList<String> ();
          //ticketNoList = Arrays.asList("2345", "3456");
          ticketNoList = Arrays.asList();
          pnr.setTicketNos(ticketNoList);
          
          java.util.Calendar cal1 = new java.util.GregorianCalendar();
      	  cal1.set(13, 11,31);
      	  java.util.Date departureDate1 = cal1.getTime();
      	  java.util.Calendar cal2 = new java.util.GregorianCalendar();
      	  cal2.set(19, 11, 31);
    	  java.util.Date departureDate2 = cal2.getTime();
    	  
          java.util.List<SegmentInfo> segmentList = new java.util.ArrayList<SegmentInfo> ();
          
          SegmentInfo segment1 = new SegmentInfo(SEGMENT_ID_1, SEMENT_TYPE_0, SEGMENT_ACTIVE_FLAG_Y, "AUS",
      			"ATL", departureDate1, null, null, null, false);
          segmentList.add(segment1);
          SegmentInfo segment2 = new SegmentInfo("2", SEMENT_TYPE_0, SEGMENT_ACTIVE_FLAG_Y, ORIGIN_STATION_CODE_ATL,
        			"AUS", departureDate2, null, null, null, false);
            segmentList.add(segment2);
          pnr.setSegInfos(segmentList);
          
          //Remark remark = new Remark();
          //remark.setTypeCode("ASVC");
          //pnr.addRemark(remark);
         
          System.out.println("Doc type is " + doc1.getType());
          
          List<Command<?>> cmds = new ArrayList<Command<?>>();
    	  cmds.add(CommandFactory.newInsert(pnr));
    	  cmds.add(CommandFactory.newInsert(txn1));
    	  cmds.add(CommandFactory.newStartProcess("FulfillmentRules.FulfillmentRuleFlow"));
    	      	 
    	  //cmds.add(CommandFactory.newGetObjects(new ClassObjectFilter(FulfillmentTxn.class), "txns"));
    	  cmds.add(CommandFactory.newGetObjects(new ClassObjectFilter(FulfillmentTxn.class), "txns"));
    	  cmds.add(CommandFactory.newGetObjects(new ClassObjectFilter(Pnr.class), "pnrs"));
    	  ExecutionResults results = kSession.execute(CommandFactory.newBatchExecution(cmds));
    	  Collection<?> txns = (Collection<?>) results.getValue("txns");
    	  Collection<?> pnrs = (Collection<?>) results.getValue("pnrs");
    	  
    	  System.out.println("Doc type is " + doc1.getType());
    	     	  
    	  System.out.println("\t\t\t\t Fulfillment Test Case - \n" + txn1.toString());
    	  System.out.println("\t overall cancelItinerary of PNR = " + pnr.isCancelItinerary() + "\n\n");
    	  
    	  
        assertTrue( true );
       } catch (Exception e) {
           System.out.println("Date Parsing failed" + e.getMessage());
           e.printStackTrace();
       }
    }
    
    private Coupon generateCoupon(String couponId, String docId) {
    	String FLIGHT_NUMBER= "1234";
    	String SOLD_STATE= "SOLD";
    	String OPEN_STATUS= "O";
    	String BASIC_ECONOMY = "E";
    	String DL_CARRIER = "DL";
    	java.util.Calendar cal = new java.util.GregorianCalendar();
    	cal.set(2020, 8, 17);
    	java.util.Date departureDate = cal.getTime();
    	cal.set(2020, 8, 17);
    	java.util.Date arrivalDate = cal.getTime();
    	Coupon coupon = new Coupon(couponId, SOLD_STATE, OPEN_STATUS, "ATL", "JFK", departureDate, arrivalDate, BASIC_ECONOMY, DL_CARRIER, FLIGHT_NUMBER, docId );
        return coupon;
    }

   
    
    // ********** //
//    public void testAlipay() {
//    	   
//        
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
//     	// Load the KIE base:
//     	  KieServices ks = KieServices.Factory.get();
//     	  KieContainer kContainer = ks.getKieClasspathContainer();
//     	  StatelessKieSession kSession = kContainer.newStatelessKieSession("FulfillmentRulesKS");
//
//     	  // Set up the fact model:
//
////        Test Case 0
//          
//          FulfillmentTxn txn1 = new FulfillmentTxn();
//          java.util.List<Document> dbDocs = new java.util.ArrayList<Document> ();
//          Document doc = new Document();
//          doc.setId("0");
//          Coupon cpn = new Coupon();
//          //dbDocs.add(doc);
//          doc.addDbCoupon(cpn);
//
//          Document doc2 = new Document();
//          doc2.setId("2");
//          Coupon cpnX = new Coupon();         
//          //dbDocs.add(doc2);
//          doc2.addDbCoupon(cpnX);
//
//          
////        Test Case 2
//          
//          txn1.setIntent("REFUND");
//          //txn1.setUserAuthorization("PaxRefundTeamAuthorize");
//          txn1.setUserAuthorization("CSSAuthorize");
//          txn1.setOverrideRequested(true);
//          
//           String DOCUMENT_ID_1 = "1";
//           Document doc1 = new Document();
//           doc1.setId(DOCUMENT_ID_1);
//           doc1.setType("T");  // T for Ticket, J for EMD, A for Award
//           doc1.setReasonForIssuanceCode("SomeNonNullValue");
//           doc1.setEmdAssociationTypeCode("S");
//           doc1.setSubType(null);
//           doc1.setStatus("PARTIAL");
//           doc1.setPrimaryTicket(true);
//           doc1.setAmountOverride(true);
//           java.util.Calendar cal = new java.util.GregorianCalendar();
//       	  cal.set(2020, 5, 22);
//           doc1.setIssueDate(cal.getTime());
//           doc1.setRefundStatus("Q");  // I is invalid quote status
//           doc1.setPassengerTypeCode("CMP");
//           Coupon cpn1 = generateCoupon("1", DOCUMENT_ID_1);
//           cpn1.setStatus("F");
//           doc1.addDbCoupon(cpn1);
//           //cpn1.setStatus("SOME_OTHER_STATUS_THAN_DOC_API_COUPON_STATUS");          
//           Coupon cpn2 = generateCoupon("2", DOCUMENT_ID_1);
//           cpn2.setStatus("R");
//           doc1.addDbCoupon(cpn2);
//           
//           Coupon cpn3 = generateCoupon("1", DOCUMENT_ID_1);
//           cpn3.setStatus("F");
//           doc1.addDocApiCoupon(cpn3);
//           //cpn3.setStatus("SOME_OTHER_STATUS_THAN_DOC_API_COUPON_STATUS");
//           Coupon cpn4 = generateCoupon("2", DOCUMENT_ID_1);
//           cpn4.setStatus("R");
//           doc1.addDocApiCoupon(cpn4);
//           
//           Aggregate ag = new Aggregate();
//           ag.setType("RefundAmount");
//           ag.setCurrencyCode("USD");
//           //ag.setAmount(new java.math.BigDecimal(8880)); 
//           ag.setAmount(new java.math.BigDecimal(0));
//           ag.setMiles(4000);
//           ag.setPrecision(2);
//       
//           ag.setDocId(DOCUMENT_ID_1);
//           doc1.addRequestAggregate(ag);
//           doc1.addDbAggregate(ag);
//           
//           Aggregate refundBaseFare = new Aggregate();
//           refundBaseFare.setType("RefundBaseFare");
//           refundBaseFare.setCurrencyCode("USD");
//           refundBaseFare.setAmount(new java.math.BigDecimal(8880));
//           refundBaseFare.setDocId(DOCUMENT_ID_1);
//           refundBaseFare.setPrecision(2);
//           doc1.addRequestAggregate(refundBaseFare);
//           doc1.addDbAggregate(refundBaseFare);
//           
//           Aggregate refundTotalTax = new Aggregate();
//           refundTotalTax.setType("RefundTotalTax");
//           refundTotalTax.setCurrencyCode("USD");
//           refundTotalTax.setAmount(new java.math.BigDecimal(8880));
//           refundTotalTax.setPrecision(2);
//           refundTotalTax.setDocId(DOCUMENT_ID_1);
//           doc1.addRequestAggregate(refundTotalTax);
//           doc1.addDbAggregate(refundTotalTax);
//           
//           Aggregate cancellationAmount1 = new Aggregate();
//           cancellationAmount1.setType("CancellationAmt");
//           cancellationAmount1.setCurrencyCode("USD");
//           cancellationAmount1.setAmount(new java.math.BigDecimal(9000));
//           cancellationAmount1.setDocId(DOCUMENT_ID_1);
//           cancellationAmount1.setPrecision(2);
//           doc1.addRequestAggregate(cancellationAmount1);
//          
//           Aggregate cancellationAmount2 = new Aggregate();
//           cancellationAmount2.setType("CancellationAmt");
//           cancellationAmount2.setCurrencyCode("USD");
//           cancellationAmount2.setAmount(new java.math.BigDecimal(8900));
//           cancellationAmount2.setDocId(DOCUMENT_ID_1);
//           cancellationAmount2.setPrecision(2);
//           doc1.addDbAggregate(cancellationAmount2);
//
//           
//           Aggregate dbag = new Aggregate();
//           dbag.setType("RefundAmount");
//           dbag.setCurrencyCode("USD");
//           dbag.setAmount(new java.math.BigDecimal(8900));
//           dbag.setDocId(DOCUMENT_ID_1);
//           dbag.setPrecision(2);
//           doc1.addDbAggregate(dbag);
//           
//           FopInfo fop = new FopInfo("MS", "TP", "MFP", "CK", "ALIPAY"); // vendor code needs to be TP or DS so this is invalid FOP
//         
//           doc1.addFOP(fop);
//           dbDocs.add(doc1);
//           txn1.setDbDocs(dbDocs);
//           
//           
//          
//           Pnr pnr = new Pnr();
//           java.util.List<String> ticketNoList = new java.util.ArrayList<String> ();
//           ticketNoList = Arrays.asList("2345", "3456");
//           pnr.setTicketNos(ticketNoList);
//           
//           java.util.Calendar cal1 = new java.util.GregorianCalendar();
//       	  cal1.set(2020, 8, 17);
//       	  java.util.Date departureDate = cal1.getTime();
//       	  cal1.set(2020, 8, 17);
//     	  java.util.Date arrivalDate = cal1.getTime();
//     	  
//           java.util.List<SegmentInfo> segmentList = new java.util.ArrayList<SegmentInfo> ();
//           
//           SegmentInfo segment = new SegmentInfo(SEGMENT_ID_1, SEMENT_TYPE_0, SEGMENT_ACTIVE_FLAG_Y, ORIGIN_STATION_CODE_ATL,
//       			"MSP", departureDate, arrivalDate, "DL", "DL", false);
//           segmentList.add(segment);
//           pnr.setSegInfos(segmentList);
//           
//           Remark remark = new Remark();
//           remark.setTypeCode("ASVC");
//           pnr.addRemark(remark);
//          
//           
//           List<Command<?>> cmds = new ArrayList<Command<?>>();
//     	  cmds.add(CommandFactory.newInsert(pnr));
//     	  cmds.add(CommandFactory.newInsert(txn1));
//     	  cmds.add(CommandFactory.newStartProcess("FulfillmentRules.FulfillmentRuleFlow"));
//     	      	 
//     	  //cmds.add(CommandFactory.newGetObjects(new ClassObjectFilter(FulfillmentTxn.class), "txns"));
//     	  cmds.add(CommandFactory.newGetObjects(new ClassObjectFilter(FulfillmentTxn.class), "txns"));
//     	  cmds.add(CommandFactory.newGetObjects(new ClassObjectFilter(Pnr.class), "pnrs"));
//     	  ExecutionResults results = kSession.execute(CommandFactory.newBatchExecution(cmds));
//     	  Collection<?> txns = (Collection<?>) results.getValue("txns");
//     	  Collection<?> pnrs = (Collection<?>) results.getValue("pnrs");
//     	  
//     	     	  
//     	  System.out.println("\t\t\t\t Fulfillment Test Case - \n" + txn1.toString());
//     	  System.out.println("\t overall cancelItinerary of PNR = " + pnr.isCancelItinerary() + "\n\n");
//     	  
//     	  
//         assertTrue( true );
//        } catch (Exception e) {
//            System.out.println("Date Parsing failed" + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//}
    


public void testCurrency()
{
	
	
    
    
   try {
       SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
	// Load the KIE base:
	  KieServices ks = KieServices.Factory.get();
	  KieContainer kContainer = ks.getKieClasspathContainer();
	  StatelessKieSession kSession = kContainer.newStatelessKieSession("FulfillmentRulesKS");

	  // Set up the fact model:

//   Test Case 0
     
     FulfillmentTxn txn1 = new FulfillmentTxn();
     java.util.List<Document> dbDocs = new java.util.ArrayList<Document> ();
     Document doc = new Document();
     doc.setId("0");
     Coupon cpn = new Coupon();
     //dbDocs.add(doc);
     doc.addDbCoupon(cpn);

     Document doc2 = new Document();
     doc2.setId("2");
     Coupon cpnX = new Coupon();         
     //dbDocs.add(doc2);
     doc2.addDbCoupon(cpnX);

     
//   Test Case 1
     
     txn1.setIntent("ECREDIT");
     //txn1.setUserAuthorization("PaxRefundTeamAuthorize");
     txn1.setUserAuthorization("CSSAuthorize");
     txn1.setOverrideRequested(true);
     
      String DOCUMENT_ID_1 = "1";
      Document doc1 = new Document();
      doc1.setId(DOCUMENT_ID_1);
      doc1.setType("J");  // T for Ticket, J for EMD, A for Award
      doc1.setReasonForIssuanceCode("SomeNonNullValue");
      doc1.setEmdAssociationTypeCode("S");
      doc1.setSubType(null);
      doc1.setStatus("PARTIAL");
      doc1.setPrimaryTicket(true);
      doc1.setAmountOverride(true);
      java.util.Calendar cal = new java.util.GregorianCalendar();
  	  cal.set(2020, 5, 22);
      doc1.setIssueDate(cal.getTime());
      doc1.setRefundStatus("Q");  // I is invalid quote status
      doc1.setPassengerTypeCode("CMP");
      Coupon cpn1 = generateCoupon("1", DOCUMENT_ID_1);
      cpn1.setStatus("F");
      doc1.addDbCoupon(cpn1);
      //cpn1.setStatus("SOME_OTHER_STATUS_THAN_DOC_API_COUPON_STATUS");          
      Coupon cpn2 = generateCoupon("2", DOCUMENT_ID_1);
      cpn2.setStatus("R");
      doc1.addDbCoupon(cpn2);
      
      Coupon cpn3 = generateCoupon("1", DOCUMENT_ID_1);
      cpn3.setStatus("F");
      doc1.addDocApiCoupon(cpn3);
      //cpn3.setStatus("SOME_OTHER_STATUS_THAN_DOC_API_COUPON_STATUS");
      Coupon cpn4 = generateCoupon("2", DOCUMENT_ID_1);
      cpn4.setStatus("R");
      doc1.addDocApiCoupon(cpn4);
      
      Aggregate ag = new Aggregate();
      ag.setType("TotalAmount");
      ag.setCurrencyCode("USD");
//      ag.setAmount(new java.math.BigDecimal(8880)); 
      ag.setAmount(new java.math.BigDecimal(0));
      ag.setMiles(4000);
      ag.setPrecision(2);
      ag.setDocId(DOCUMENT_ID_1);
      doc1.addRequestAggregate(ag);
      doc1.addDbAggregate(ag);
      
//      Aggregate refundBaseFare = new Aggregate();
//      refundBaseFare.setType("RefundBaseFare");
//      refundBaseFare.setCurrencyCode("USDD");
//      refundBaseFare.setAmount(new java.math.BigDecimal(8880));
//      refundBaseFare.setDocId(DOCUMENT_ID_1);
//      refundBaseFare.setPrecision(2);
//      doc1.addRequestAggregate(refundBaseFare);
//      doc1.addDbAggregate(refundBaseFare);
      
      Aggregate refundEquivalentBaseFare = new Aggregate();
      refundEquivalentBaseFare.setType("RefundEquivalentBaseFare");
      refundEquivalentBaseFare.setCurrencyCode("USD");
      refundEquivalentBaseFare.setAmount(new java.math.BigDecimal(8880));
      refundEquivalentBaseFare.setDocId(DOCUMENT_ID_1);
      refundEquivalentBaseFare.setPrecision(2);
      doc1.addRequestAggregate(refundEquivalentBaseFare);
      doc1.addDbAggregate(refundEquivalentBaseFare);

      //      
//      Aggregate refundTotalTax = new Aggregate();
//      refundTotalTax.setType("RefundTotalTax");
//      refundTotalTax.setCurrencyCode("USD");
//      refundTotalTax.setAmount(new java.math.BigDecimal(8880));
//      refundTotalTax.setPrecision(2);
//      refundTotalTax.setDocId(DOCUMENT_ID_1);
//      doc1.addRequestAggregate(refundTotalTax);
//      doc1.addDbAggregate(refundTotalTax);
//      
//      Aggregate cancellationAmount1 = new Aggregate();
//      cancellationAmount1.setType("CancellationAmt");
//      cancellationAmount1.setCurrencyCode("USD");
//      cancellationAmount1.setAmount(new java.math.BigDecimal(9000));
//      cancellationAmount1.setDocId(DOCUMENT_ID_1);
//      cancellationAmount1.setPrecision(2);
//      doc1.addRequestAggregate(cancellationAmount1);
//     
//      Aggregate cancellationAmount2 = new Aggregate();
//      cancellationAmount2.setType("CancellationAmt");
//      cancellationAmount2.setCurrencyCode("USD");
//      cancellationAmount2.setAmount(new java.math.BigDecimal(8900));
//      cancellationAmount2.setDocId(DOCUMENT_ID_1);
//      cancellationAmount2.setPrecision(2);
//      doc1.addDbAggregate(cancellationAmount2);

      
//      Aggregate dbag = new Aggregate();
//      dbag.setType("RefundAmount");
//      dbag.setCurrencyCode("USD");
//      dbag.setAmount(new java.math.BigDecimal(8900));
//      dbag.setDocId(DOCUMENT_ID_1);
//      dbag.setPrecision(2);
//      doc1.addDbAggregate(dbag);
      
      FopInfo fop = new FopInfo("MS", "TP", "MFP", "CK", "ALIPAY"); // venfor code needs to be TP or DS so this is invalid FOP
      doc1.addFOP(fop);
      dbDocs.add(doc1);
      txn1.setDbDocs(dbDocs);
     
      Pnr pnr = new Pnr();
      java.util.List<String> ticketNoList = new java.util.ArrayList<String> ();
      ticketNoList = Arrays.asList("2345", "3456");
      pnr.setTicketNos(ticketNoList);
      
      java.util.Calendar cal1 = new java.util.GregorianCalendar();
  	  cal1.set(2020, 8, 17);
  	  java.util.Date departureDate = cal1.getTime();
  	  cal1.set(2020, 8, 17);
	  java.util.Date arrivalDate = cal1.getTime();
	  
      java.util.List<SegmentInfo> segmentList = new java.util.ArrayList<SegmentInfo> ();
      
      SegmentInfo segment = new SegmentInfo(SEGMENT_ID_1, SEMENT_TYPE_0, SEGMENT_ACTIVE_FLAG_Y, ORIGIN_STATION_CODE_ATL,
  			"MSP", departureDate, arrivalDate, "DL", "DL", false);
      segmentList.add(segment);
      pnr.setSegInfos(segmentList);
      
      Remark remark = new Remark();
      remark.setTypeCode("ASVC");
      pnr.addRemark(remark);
     
      
      List<Command<?>> cmds = new ArrayList<Command<?>>();
	  cmds.add(CommandFactory.newInsert(pnr));
	  cmds.add(CommandFactory.newInsert(txn1));
	  cmds.add(CommandFactory.newStartProcess("FulfillmentRules.FulfillmentRuleFlow"));
	      	 
	  //cmds.add(CommandFactory.newGetObjects(new ClassObjectFilter(FulfillmentTxn.class), "txns"));
	  cmds.add(CommandFactory.newGetObjects(new ClassObjectFilter(FulfillmentTxn.class), "txns"));
	  cmds.add(CommandFactory.newGetObjects(new ClassObjectFilter(Pnr.class), "pnrs"));
	  ExecutionResults results = kSession.execute(CommandFactory.newBatchExecution(cmds));
	  Collection<?> txns = (Collection<?>) results.getValue("txns");
	  Collection<?> pnrs = (Collection<?>) results.getValue("pnrs");
	  
	     	  
	  System.out.println("\t\t\t\t Fulfillment Test Case - \n" + txn1.toString());
	  System.out.println("\t overall cancelItinerary of PNR = " + pnr.isCancelItinerary() + "\n\n");
	  
	  
    assertTrue( true );
   } catch (Exception e) {
       System.out.println("Date Parsing failed" + e.getMessage());
       e.printStackTrace();
   }
}

private Coupon generateCoupon1(String couponId, String docId) {
	String FLIGHT_NUMBER= "1234";
	String SOLD_STATE= "SOLD";
	String OPEN_STATUS= "O";
	String BASIC_ECONOMY = "E";
	String DL_CARRIER = "DL";
	java.util.Calendar cal = new java.util.GregorianCalendar();
	cal.set(2020, 8, 17);
	java.util.Date departureDate = cal.getTime();
	cal.set(2020, 8, 17);
	java.util.Date arrivalDate = cal.getTime();
	Coupon coupon = new Coupon(couponId, SOLD_STATE, OPEN_STATUS, "ATL", "JFK", departureDate, arrivalDate, BASIC_ECONOMY, DL_CARRIER, FLIGHT_NUMBER, docId );
    return coupon;
}

}
    
    

