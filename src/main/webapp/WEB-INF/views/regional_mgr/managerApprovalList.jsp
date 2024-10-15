<%@ page import="bank.app.entities.User"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manager Approval List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/managerapprovallist.css">
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
        <h1>Managers Needing Approval</h1>
    </header>

    <div class="approval-container">
        <h2>Manager Approval List</h2>

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
                List<User> managerApprovalList = (List<User>) session.getAttribute("managerApprovalList");

                if (managerApprovalList != null && !managerApprovalList.isEmpty()) {
                    for (User manager : managerApprovalList) {
                %>
                <tr>
                    <td><%=manager.getUsername()%></td>
                    <td><%=manager.getFirstName()%></td>
                    <td><%=manager.getLastName()%></td>
                    <td><%=manager.getEmail()%></td>
                    <td>
                        <form action="/regional_mgr/managerApprove/<%=manager.getUserId()%>"
                            method="post" style="display: inline-block;"
                            onsubmit="return handleApproval(this.querySelector('button'));">
                            <input type="hidden" name="userId"
                                value="<%=manager.getUserId()%>">
                            <button class="btn">Approve</button>
                        </form>

                        <form action="/regional_mgr/managerReject/<%=manager.getUserId()%>" method="post"
                            style="display: inline-block;">
                            <input type="hidden" name="userId"
                                value="<%=manager.getUserId()%>">
                            <button class="btn">Reject</button>
                        </form>
                    </td>
                </tr>
                <%
                }
                } else {
                %>
                <tr>
                    <td colspan="6">No managers waiting for approval.</td>
                </tr>
                <%
                }
                %>
            </tbody>
        </table>
    </div>

    <div style="text-align: center; margin-top: 20px;">
        <button class="btn"
            onclick="window.location.href='/regional_mgr/dashboard'">Go to Dashboard</button>
    </div>
</body>
</html>
