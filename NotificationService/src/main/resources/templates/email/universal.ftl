<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${subject!"Notification"}</title>
</head>

<body style="margin:0; padding:0; background-color:#f4f6f8; font-family: Arial, Helvetica, sans-serif;">

<table width="100%" cellpadding="0" cellspacing="0">
    <tr>
        <td align="center">

            <!-- MAIN CONTAINER -->
            <table width="600" cellpadding="0" cellspacing="0"
                   style="background:#ffffff; margin:30px 0; border-radius:8px; overflow:hidden; box-shadow:0 4px 12px rgba(0,0,0,0.08);">

                <!-- HEADER -->
                <tr>
                    <td style="background:#0d6efd; padding:20px; text-align:center; color:#ffffff;">
                        <h2 style="margin:0;">${appName!"Notification Service"}</h2>
                        <p style="margin:5px 0 0; font-size:14px;">
                            ${tagline!"Reliable & Secure Communication"}
                        </p>
                    </td>
                </tr>

                <!-- BODY -->
                <tr>
                    <td style="padding:30px; color:#333333;">

                        <!-- GREETING -->
                        <p style="font-size:15px;">
                            Hello <b>${name!"User"}</b>,
                        </p>

                        <!-- MAIN MESSAGE -->
                        <p style="font-size:15px; line-height:1.6;">
                            ${message!"We have an update for you."}
                        </p>

                        <!-- HIGHLIGHT (OTP / OFFER / CODE) -->
                        <#if highlight??>
                        <div style="margin:25px 0; padding:20px; background:#f1f5ff;
                                    border-left:5px solid #0d6efd; text-align:center;">
                            <h1 style="margin:0; color:#0d6efd; letter-spacing:2px;">
                                ${highlight}
                            </h1>

                            <#if validity??>
                            <p style="margin-top:10px; font-size:13px; color:#555;">
                                Valid for: ${validity}
                            </p>
                            </#if>
                        </div>
                        </#if>

                        <!-- CTA BUTTON -->
                        <#if actionText?? && actionUrl??>
                        <div style="text-align:center; margin:30px 0;">
                            <a href="${actionUrl}"
                               style="background:#0d6efd; color:#ffffff;
                                      text-decoration:none; padding:12px 24px;
                                      border-radius:5px; font-size:15px;
                                      display:inline-block;">
                                ${actionText}
                            </a>
                        </div>
                        </#if>

                        <!-- TRANSACTION / REFERENCE -->
                        <#if referenceId?? || amount??>
                        <div style="margin-top:20px; font-size:14px; color:#555;">
                            <#if referenceId??>
                                <p><b>Reference ID:</b> ${referenceId}</p>
                            </#if>
                            <#if amount??>
                                <p><b>Amount:</b> ${amount}</p>
                            </#if>
                        </div>
                        </#if>

                        <!-- DATE / TIME -->
                        <#if date?? || time??>
                        <p style="font-size:13px; color:#666;">
                            <#if date??>Date: ${date}</#if>
                            <#if time??> | Time: ${time}</#if>
                        </p>
                        </#if>

                        <!-- SUPPORT INFO -->
                        <#if supportEmail?? || supportPhone??>
                        <p style="font-size:13px; color:#666;">
                            Need help?
                            <#if supportEmail??>
                                Email: ${supportEmail}
                            </#if>
                            <#if supportPhone??>
                                | Phone: ${supportPhone}
                            </#if>
                        </p>
                        </#if>

                        <!-- FOOTER MESSAGE -->
                        <p style="font-size:14px; color:#666; margin-top:25px;">
                            ${footerMessage!"If you have any questions, feel free to contact us."}
                        </p>

                        <!-- SIGNATURE -->
                        <p style="margin-top:30px;">
                            Regards,<br>
                            <b>${teamName!"Notification Team"}</b>
                        </p>

                    </td>
                </tr>

                <!-- FOOTER -->
                <tr>
                    <td style="background:#f8f9fa; padding:15px; text-align:center;
                               font-size:12px; color:#777;">
                        © ${year} ${companyName!"Your Company"} • All rights reserved
                    </td>
                </tr>

            </table>

        </td>
    </tr>
</table>

</body>
</html>
