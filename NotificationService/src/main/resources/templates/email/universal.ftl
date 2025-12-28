<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${subject!"Notification"}</title>
</head>

<body style="margin:0; padding:0; background-color:#eef2f6;
             font-family: Arial, Helvetica, sans-serif;">

<table width="100%" cellpadding="0" cellspacing="0" style="background-color:#eef2f6;">
    <tr>
        <td align="center">

            <!-- MAIN CONTAINER -->
            <table width="600" cellpadding="0" cellspacing="0"
                   style="background:#ffffff; margin:40px 0;
                          border-radius:10px; overflow:hidden;
                          box-shadow:0 8px 24px rgba(0,0,0,0.08);">

                <!-- HEADER -->
                <tr>
                    <td style="background:linear-gradient(135deg,#0d6efd,#0047ab);
                               padding:26px; text-align:center; color:#ffffff;">

                        <!-- LOGO (INLINE CID) -->
                        <img src="cid:logo"
                             height="48"
                             alt="Logo"
                             style="display:block; margin:0 auto 12px auto;" />

                        <h1 style="margin:0; font-size:22px; font-weight:600;">
                            ${appName!"Notification Service"}
                        </h1>

                        <p style="margin:6px 0 0; font-size:14px; opacity:0.9;">
                            ${tagline!"Reliable & Secure Communication"}
                        </p>
                    </td>
                </tr>

                <!-- BODY -->
                <tr>
                    <td style="padding:34px; color:#333333;">

                        <!-- GREETING -->
                        <p style="font-size:16px; margin-top:0;">
                            Hello <b>${name!"User"}</b>,
                        </p>

                        <!-- MESSAGE -->
                        <p style="font-size:15px; line-height:1.7;">
                            ${message!"We have an important update for you."}
                        </p>

                        <!-- HIGHLIGHT / OTP / CODE -->
                        <#if highlight??>
                        <div style="margin:30px 0; padding:24px;
                                    background:#f8faff; border-radius:8px;
                                    border:1px dashed #0d6efd; text-align:center;">

                            <div style="font-size:13px; color:#555;">
                                ${highlightLabel!"Important Code"}
                            </div>

                            <div style="font-size:30px; font-weight:700;
                                        letter-spacing:4px; color:#0d6efd;
                                        margin-top:6px;">
                                ${highlight}
                            </div>

                            <#if validity??>
                            <div style="margin-top:10px; font-size:13px; color:#666;">
                                Valid for: ${validity}
                            </div>
                            </#if>
                        </div>
                        </#if>

                        <!-- CTA BUTTON -->
                        <#if actionText?? && actionUrl??>
                        <div style="text-align:center; margin:36px 0;">
                            <a href="${actionUrl}"
                               style="background:#0d6efd; color:#ffffff;
                                      text-decoration:none; padding:14px 34px;
                                      border-radius:6px; font-size:15px;
                                      font-weight:600; display:inline-block;">
                                ${actionText}
                            </a>
                        </div>
                        </#if>

                        <!-- TRANSACTION DETAILS -->
                        <#if referenceId?? || amount??>
                        <table width="100%" cellpadding="0" cellspacing="0"
                               style="margin-top:22px;
                                      border:1px solid #e6e6e6;
                                      border-radius:6px;">
                            <#if referenceId??>
                            <tr>
                                <td style="padding:12px 14px; font-size:14px;">
                                    <b>Reference ID</b>
                                </td>
                                <td style="padding:12px 14px; font-size:14px;">
                                    ${referenceId}
                                </td>
                            </tr>
                            </#if>
                            <#if amount??>
                            <tr>
                                <td style="padding:12px 14px; font-size:14px;">
                                    <b>Amount</b>
                                </td>
                                <td style="padding:12px 14px; font-size:14px;">
                                    ${amount}
                                </td>
                            </tr>
                            </#if>
                        </table>
                        </#if>

                        <!-- DATE / TIME -->
                        <#if date?? || time??>
                        <p style="margin-top:20px; font-size:13px; color:#666;">
                            <#if date??>📅 ${date}</#if>
                            <#if time??> | ⏰ ${time}</#if>
                        </p>
                        </#if>

                        <!-- SUPPORT -->
                        <#if supportEmail?? || supportPhone??>
                        <p style="margin-top:22px; font-size:13px; color:#666;">
                            Need help?
                            <#if supportEmail??>
                                <br>Email:
                                <a href="mailto:${supportEmail}">
                                    ${supportEmail}
                                </a>
                            </#if>
                            <#if supportPhone??>
                                <br>Phone: ${supportPhone}
                            </#if>
                        </p>
                        </#if>

                        <!-- FOOTER MESSAGE -->
                        <p style="margin-top:30px; font-size:14px; color:#666;">
                            ${footerMessage!"If you did not request this, please ignore this email."}
                        </p>

                        <!-- SIGNATURE -->
                        <p style="margin-top:36px;">
                            Regards,<br>
                            <b>${teamName!"Notification Team"}</b>
                        </p>

                    </td>
                </tr>

                <!-- FOOTER -->
                <tr>
                    <td style="background:#f3f5f8; padding:16px;
                               text-align:center; font-size:12px; color:#777;">
                        © ${year} ${companyName!"Your Company"} • All rights reserved
                    </td>
                </tr>

            </table>

        </td>
    </tr>
</table>

</body>
</html>
