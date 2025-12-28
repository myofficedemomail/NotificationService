package com.proj.notification.dto;

import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EmailTemplateVariables {

    @Schema(description = "Receiver name", example = "Subha")
    private String name;

    @Schema(description = "Main message content", example = "Please find the details below.")
    private String message;

    //HIGHLIGHT / OTP

    @Schema(description = "Highlighted value (OTP / Code / Offer)", example = "482931")
    private String highlight;

    @Schema(description = "OTP expiry or offer validity", example = "5 minutes")
    private String validity;

    // ORGANIZATION

    @Schema(description = "Application or portal name", example = "College Portal")
    private String appName;

    @Schema(description = "Company / Organization name", example = "XYZ College")
    private String companyName;

    @Schema(description = "Team / Department name", example = "Administration Team")
    private String teamName;



    @Schema(description = "Button text (Verify, Download, View)", example = "Verify OTP")
    private String actionText;

    @Schema(description = "Button URL", example = "https://portal.xyz.com/verify")
    private String actionUrl;

    //TRANSACTION / INVOICE 

    @Schema(description = "Transaction / Reference ID", example = "TXN-2025-001")
    private String referenceId;

    @Schema(description = "Amount (Invoice / Payment)", example = "₹1500")
    private String amount;

    // DATE / TIME

    @Schema(description = "Event / Notice date", example = "28 Dec 2025")
    private String date;

    @Schema(description = "Event time", example = "10:30 AM")
    private String time;

    // FOOTER / SUPPORT

    @Schema(description = "Support email", example = "support@xyz.com")
    private String supportEmail;

    @Schema(description = "Support phone", example = "+91-9876543210")
    private String supportPhone;
    
    @Schema(description = "Custom extra variables")
    private Map<String, String> extra;

    
    
}
