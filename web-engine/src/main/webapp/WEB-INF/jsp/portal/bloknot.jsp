<!doctype html>
<%@ page import="java.text.*,java.util.*" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Bloknot</title>

        <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    </head>
    <body>
        <div class="logout-div">
            <a class="logout-button" href="/portal/user/logout">Logout</a>
        </div>
        <div id="header-div">
            <div class="table">
                <div class="table-header">
                    <span class="fa fa-calendar" aria-hidden="true"></span><span>Bloknot</span><span  class="fa fa-calendar" aria-hidden="true"></span><span  class="fa fa-calendar" aria-hidden="true"></span>

                </div>
                <form  name="form" id="save-form"><div class="heder-add">
                        <input class="input-task" type="text" name="text"/>
                        <button class="add-task save-button" onclick="save()" href="#" type="button">Add record</button>
                    </div> </form>
                <table class="simple-little-table" id="task-table">
                    <tr>
                        <th style="width: 10%"> </th>
                        <th ></th>
                        <th style="width: 10%"></th>
                        <th style="width: 10%"></th>
                    </tr>
                    <c:forEach var="record" items="${records}"> 
                        <tr id="record-tr-${record.id}">
                            <td>
                                <fmt:formatDate pattern="dd.MM.yyyy" value="${record.date}"/> в <fmt:formatDate pattern="HH:mm" value="${record.date}"/>
                            </td>
                            <td id="task-${record.id}">
                                <input id="input-${record.id}" class="input-text" type="text" name="text" value="${record.text}"> 
                            </td>

                            <td id="edit-${record.id}" onclick="edit(${record.id});"> Edit </td>
                            <td> <a href="#" onclick="deleteRecord(${record.id});">Delete</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <script src="<c:url value="/js/libs/jquery/jquery.min.js" />"></script>
            <script>
                                var deleteRecord = function (id) {
                                    $.post('${pageContext.servletContext.contextPath}/portal/user/delete', {id: id}, function (data) {
                                        if (data === "ok") {
                                            $("#record-tr-" + id).remove();
                                        }
                                    });
                                };
                                var edit = function (id) {
                                    var text = $("#input-" + id).val();
                                    console.log(text);
                                    $.post('${pageContext.servletContext.contextPath}/portal/user/edit', {id: id, text: text}, function (data) {

                                    });
                                };
                                var save = function (id) {
                                    var formURL = "<c:url value="/portal/user/add"/>";
                                    var posting = $.post(formURL, $("#save-form").serialize());
                                    posting.done(function (data) {
                                        console.log(data);
                                        var d1 = data.date;
                                        d1.toString('dd-MM-yyyy');
                                        $('#task-table tr:last').after('<tr id="record-tr-' + data.id + '">\n\
        <td>  ' + d1 + ' </td>\n\
        <td><input id="input-' + data.id + '" class="input-text" type="text" name="text" value="' + data.text + '"></td>\n\
        \n\
        <td><a href="#" onclick="edit(' + data.id + ')";> Edit </a></td>\n\
        <td> <a href="#" onclick="deleteRecord(' + data.id + ')";>Delete</a></td>\n\
        </tr>');
                                    });
                                };
            </script>
    </body>
</html>
