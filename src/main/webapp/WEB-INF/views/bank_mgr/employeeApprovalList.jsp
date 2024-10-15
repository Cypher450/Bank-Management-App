<%@ page import="bank.app.entities.User"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Approval List</title>
    <link rel="stylesheet" href="../css/employeeapprovallist.css">
    <script>
        function handleApproval(button) {
            const actionCell = button.closest('td');
            button.disabled = true;
            return true; 
        }
    </script>
</head>
<body>
    <header>
        <h1>Employees Needing Approval</h1>
    </header>

    <div class="approval-container">
        <h2>Employee Approval List</h2>

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
                List<User> employeeApprovalList = (List<User>) session.getAttribute("employeeApprovalList");

                if (employeeApprovalList != null && !employeeApprovalList.isEmpty()) {
                    for (User employee : employeeApprovalList) {
                %>
                <tr>
                    <td><%=employee.getUsername()%></td>
                    <td><%=employee.getFirstName()%></td>
                    <td><%=employee.getLastName()%></td>
                    <td><%=employee.getEmail()%></td>
                    <td>
                        <form action="/bank_mgr/employeeApprove/<%= employee.getUserId()%>"
                            method="post" style="display: inline-block;"
                            onsubmit="return handleApproval(this.querySelector('button'));">
                            <input type="hidden" name="userId"
                                value="<%=employee.getUserId()%>">
                            <button class="btn">Approve</button>
                        </form>

                        <form action="/bank_mgr/employeeReject/<%= employee.getUserId()%>" method="post"
                            style="display: inline-block;">
                            <input type="hidden" name="userId"
                                value="<%=employee.getUserId()%>">
                            <button class="btn">Reject</button>
                        </form>  
                    </td>
                </tr>
                <%
                }
                } else {
                %>
                <tr>
                    <td colspan="6">No employees waiting for approval.</td>
                </tr>
                <%
                }
                %>
            </tbody>
        </table>
    </div>

    <div style="text-align: center; margin-top: 20px;">
        <button class="btn"
            onclick="window.location.href='/bank_mgr/dashboard'">Go to Dashboard</button>
    </div>
</body>
</html>
