[condition][]When there exists a FulfillmentTxn where=transaction:FulfillmentTxn()
[condition][]- that fulfillment transaction is validated= isValidated()
[condition][]- that transaction's  user authorization is null=userAuthorization == null
[condition][]- that transaction's  requestType is null=requestType == null
[condition][]- that transaction's  database document list is empty=isDbDocsListEmpty()

[condition][]When there exists a Database Document on Fulfillment transaction where=document:Document() from FulfillmentTxn.getDbDocs()
[condition][]- that document is validated=ruleResult != null && isValidated()
[condition][]- that document's id is null=id == null
[condition][]- that document's type is null=type == null
[condition][]- that document's status is null=status == null
[condition][]- that document's owning carrier code is null=owningCarrierCode == null
[condition][]- that document's coupon list is empty=document.isCouponListEmpty()
[condition][]- that document's aggregate list is empty=document.isAggregateListEmpty()
[condition][]- that document's aggregate list is not empty=!document.isAggregateListEmpty()
[condition][]- that document's tax component list is empty=document.isTaxComponentListEmpty()
[condition][]- that document is an IEMD or a Ticket=type != null && (type.equalsIgnoreCase("IEMD") || type.equalsIgnoreCase("TICKET"))
[condition][]- that document has more than one coupons=coupons != null && coupons.size() > 1
[condition][]- that document's type is {type:ENUM:Document.type}=type == "{type}"
[condition][]- or the type is {type:ENUM:Document.type}=|| type == "{type}"
[condition][]- that document's status is {status:ENUM:Document.status}=status == "{status}"

[condition][]When there exists a database coupon of the Document where=coupon:Coupon() from document.getDbCoupons()
[condition][]When there exists a doc api coupon of the Document where=coupon:Coupon() from document.getDocApiCoupons()
[condition][]- that coupon's id is not present=id == null || ( id != null && status.length() == 0)
[condition][]- that coupon's state is not present=state == null || ( state != null && state.length() == 0)
[condition][]- that coupon's status is not present=status == null || ( status != null && status.length() == 0)
[condition][]- that coupon's origin station code is not present=originStationCode == null || ( originStationCode != null && originStationCode.length() == 0)
[condition][]- that coupon's destination station code is not present=destinationStationCode == null || ( destinationStationCode != null && destinationStationCode.length() == 0)
[condition][]- that coupon's departure date is not present=departureDate == null
[condition][]- that coupon's arrival date is not present=arrivalDate == null 
[condition][]- that coupon's class of service is not present=cos == null || ( cos != null && cos.length() == 0)
[condition][]- that coupon's marketing carrier code is not present=marketingCarrierCode == null || ( marketingCarrierCode != null && marketingCarrierCode.length() == 0)
[condition][]- that coupon's flight number is not present=flightNum == null || ( flightNum != null && flightNUm.length() == 0)

[condition][]When there exists a request aggregate of the Document where=aggregate:Aggregate() from document.getRequestAggregates()
[condition][]When there exists a database aggregate of the Document where=aggregate:Aggregate() from document.geDbAggregates()
[condition][]- that aggregate's type is not present=type == null || (type != null && type.lenght() == 0)
[condition][]- that aggregate's required precision is not present=requiredPrecision == null
[condition][]- that aggregate's amount is not present=amount == null
[condition][]- that aggregate's currencyCode is not present=currencyCode == null || (currencyCode != null && currencyCode.length() == 0)

[condition][]- that aggregate currencyCode is {crncyCode:ENUM:Aggregate.currencyCode}=currencyCode.equalsIgnoreCase("{crncyCode}")
[condition][]- that aggregate's type is {type:ENUM:Aggregate.type}=type != null && type.equalsIgnoreCase("{type}")
[condition][]When there exists a 'REFUND_BASE_FARE' aggregate=residualBaseFare : Aggregate (type matches "REFUND_BASE_FARE") from document.aggregates

[condition][]When there exists a TaxComponent of the Document where=taxComponent:TaxComponent() from document.getTaxComponents()
[condition][]- that tax components's type is null=type == null
[condition][]- that tax component's required precision is null=requiredPrecision == null
[condition][]- that tax component's amount is null=amount == null
[condition][]- that tax component's currencyCode is null=currencyCode == null
[condition][]- that tax component's type is {type:ENUM:TaxComponent.type}=type.equalsIgnoreCase("{type}")

[condition][]When there does not exist a non {type:ENUM:TaxComponent.type} tax component=not (TaxComponent(!type.equalsIgnoreCase("{type}")) from document.getTaxComponents())
[condition][]When there exists a non {type:ENUM:TaxComponent.type} tax component of the Document=exists (nontaxComponent:TaxComponent(!type.equalsIgnoreCase("{type}")) from document.getTaxComponents())
[condition][]When there does not exist an aggregate of type {type:ENUM:Aggregate.type}=not (Aggregate(type != null && type.equalsIgnoreCase("{type}")) from document.getAggregates())
[condition][]When Value Calculation request type is {type:ENUM:ValueCalculationContext.requestType}=calcContext:ValueCalculationContext(requestType == "{type}")
[condition][]When the sum of EMD Coupon Amount for coupons selected for refund exists=sumEmdCouponAmount: java.math.BigDecimal() from document.getSumEmdCouponAmount(true)
[consequence][]set the residual total tax to {percent} percent of Sum EMD coupon amounts for coupons selected for refund=aggregate.setAmountAsPercentof("{percent}", sumEmdCouponAmount)

[consequence][]note rule execution is successful=modify(transaction) { setTxnResponse(transaction.addRuleExecutionResult(true)) }
[consequence][]note rule execution is not successful=modify(transaction) { setTxnResponse(transaction.addRuleExecutionResult(false)) }

[consequence][]note document validation is successful=modify(document) { setRuleResult(document.addRuleExecutionResult(true))  }
[consequence][]note document validation is not successful=modify(document) { setRuleResult(document.addRuleExecutionResult(false)) }
