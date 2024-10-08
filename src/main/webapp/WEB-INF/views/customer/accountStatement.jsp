
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account Statement - XYZ Bank</title>
    <link rel="stylesheet" href="../css/accountstatement.css">
</head>
<body>

<header>
    <h1>Account Statement - XYZ Bank</h1>
</header>

<div class="account-details">
    <h2>Account Information</h2>
    <div class="account-info">
        <p><strong>Account Holder:</strong> John Doe</p>
        <p><strong>Account Number:</strong> XXXX-XXXX-5678</p>
        <p><strong>Account Type:</strong> Savings Account</p>
        <p><strong>IFSC Code:</strong> XYZB0001234</p>
    </div>

    <h2>Account Summary</h2>
    <div class="summary">
        <p><strong>Opening Balance:</strong> ₹20,000</p>
        <p><strong>Total Credits:</strong> ₹25,000</p>
        <p><strong>Total Debits:</strong> ₹15,000</p>
        <p><strong>Closing Balance:</strong> ₹30,000</p>
    </div>
</div>

<div class="transaction-details">
    <h2>Transaction History</h2>

    <!-- Transaction Table -->
    <table>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Type</th>
            <th>Amount</th>
            <th>Balance</th>
        </tr>

        <!-- Sample Rows: You will dynamically generate these based on DB data -->
        <tr>
            <td>2024-01-01</td>
            <td>Direct Deposit - Salary</td>
            <td>Credit</td>
            <td>₹25,000</td>
            <td>₹45,000</td>
        </tr>
        <tr>
            <td>2024-01-02</td>
            <td>ATM Withdrawal</td>
            <td>Debit</td>
            <td>₹5,000</td>
            <td>₹40,000</td>
        </tr>
        <tr>
            <td>2024-01-05</td>
            <td>Grocery Purchase</td>
            <td>Debit</td>
            <td>₹3,000</td>
            <td>₹37,000</td>
        </tr>

    </table>

    <!-- No transactions message (if applicable) -->
    <div class="no-transactions">No transactions available for this period.</div>
</div>

<!-- Print Button -->
<div class="print-btn">
    <button onclick="window.print()">Print Statement</button>
</div>
<%@include file="../message.jsp" %>
<!-- Footer -->
<div class="footer">
    <p>&copy; 2024 XYZ Bank. All rights reserved.</p>
</div>

</body>
</html>