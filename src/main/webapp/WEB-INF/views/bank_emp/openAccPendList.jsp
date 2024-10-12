<%@ page import="bank.app.entities.User"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Savings Account List</title>
<link rel="stylesheet" href="../css/customerapprovallist.css">
<script>
    function handleOpenAccount(button) {
        const actionCell = button.closest('td');
        
        button.textContent = 'Processing...';
        button.disabled = true;

        return true; 
    }
</script>
</head>
<body>
    <header>
        <h1>Customers Ready to Open Savings Account</h1>
    </header>

    <div class="approval-container">
        <h2>Savings Account List</h2>

        <table>
            <thead>
                <tr>
                    <th>Username</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                List<User> savingsAccountList = (List<User>) session.getAttribute("savingsAccountOpeningList");

                if (savingsAccountList != null && !savingsAccountList.isEmpty()) {
                    for (User customer : savingsAccountList) {
                %>
                <tr>
                    <td><%=customer.getUsername()%></td>
                    <td><%=customer.getFirstName()%></td>
                    <td><%=customer.getLastName()%></td>
                    <td><%=customer.getEmail()%></td>
                    <td>
                        <form action="/bank_emp/employee-open-savings-account-form/<%=customer.getUserId()%>"
                            method="get" style="display: inline-block;"
                            onsubmit="return handleOpenAccount(this.querySelector('button'));">
                            <input type="hidden" name="userId"
                                value="<%=customer.getUserId()%>">
                            <button class="btn">Open Savings Account</button>
                        </form>
                    </td>
                </tr>
                <%
                }
                } else {
                %>
                <tr>
                    <td colspan="5">No customers for opening a savings account.</td>
                </tr>
                <%
                }
                %>
            </tbody>
        </table>
    </div>

    <div style="text-align: center; margin-top: 20px;">
        <button class="btn"
            onclick="window.location.href='/bank_emp/dashboard'">Go to
            Dashboard</button>
    </div>
</body>
</html>
