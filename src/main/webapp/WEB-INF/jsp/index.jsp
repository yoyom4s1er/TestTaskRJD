<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 28.11.2023
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function (){
        $('#GetAllButton').click(function (){
            $.ajax({
                type:'GET',
                url:'api/v1/administrations',
                headers:{
                    Accept : "application/json; charset=utf8",
                    "Content-Type" : "application/json; charset=utf8"
                },
                success: function (result) {
                    document.getElementById('result').textContent = JSON.stringify(result, null, '\t');
                }
            })
        })
    })
</script>
<script type="text/javascript">
    $(document).ready(function (){
        $('#GetByCodeButton').click(function (){
            $.ajax({
                type:'GET',
                url:'api/v1/administrations/' + document.getElementById('Selector1').value + "/" + document.getElementById('GetByCodeField').value,
                headers:{
                    Accept : "application/json; charset=utf8",
                    "Content-Type" : "application/json; charset=utf8"
                },
                success: function (result) {
                    document.getElementById('result').textContent = JSON.stringify(result, null, '\t');
                },
                error: function (result) {
                    document.getElementById('result').textContent = result.responseText;
                }
            })
        })
    })
</script>
<script type="text/javascript">
    $(document).ready(function (){
        $('#InsertButton').click(function (){
            $.ajax({
                type:'POST',
                url:'api/v1/administrations',
                headers:{
                    Accept : "application/json; charset=utf8",
                    "Content-Type" : "application/json; charset=utf8"
                },
                dataType:'text',
                data: JSON.stringify(
                    {
                        "code": document.getElementById('CodeField').value,
                        "fullName": document.getElementById('FullNameField').value,
                        "abbreviation": document.getElementById('ABField').value,
                    }
                ),
                success: function (result) {
                    document.getElementById('result').textContent = result;
                },
                error: function (result) {
                    document.getElementById('result').textContent = result.responseText + "\n" + "Status code: " + result.status;
                    console.log(result)
                }
            })
        })
    })
</script>
<script type="text/javascript">
    $(document).ready(function (){
        $('#DeleteButton').click(function (){
            $.ajax({
                type:'DELETE',
                url:'api/v1/administrations/code/' + document.getElementById('DeleteField').value,
                headers:{
                    Accept : "application/json; charset=utf8",
                    "Content-Type" : "application/json; charset=utf8"
                },
                success: function (result) {
                    document.getElementById('result').textContent = result.responseText;
                },
                error: function (result) {
                    document.getElementById('result').textContent = result.responseText;
                }
            })
        })
    })
</script>
<script type="text/javascript">
    $(document).ready(function (){
        $('#UpdateButton').click(function (){
            $.ajax({
                type:'PUT',
                url:'api/v1/administrations/code/' + document.getElementById('UpdateCodeField2').value,
                headers:{
                    Accept : "application/json; charset=utf8",
                    "Content-Type" : "application/json; charset=utf8"
                },
                dataType:'text',
                data: JSON.stringify(
                    {
                        "код ЖА": document.getElementById('UpdateCodeField').value,
                        "полное наименование ЖА": document.getElementById('UpdateFullNameField').value,
                        "аббревиатура ЖА": document.getElementById('UpdateABField').value,
                    }
                ),
                success: function (result) {
                    document.getElementById('result').textContent = result;
                },
                error: function (result) {
                    document.getElementById('result').textContent = result.responseText + "\n" + "Status code: " + result.status;
                }
            })
        })
    })
</script>
<head>
    <style><%@include file="/WEB-INF/jsp/style.css"%></style>
    <title>Title</title>
</head>
<body>
<div style="display: flex; flex-direction: column; align-items: start; justify-content: center; width: 100%; margin-left: auto;margin-right: auto">
    <div class="queryLine">
        <input class="button" type="button" value="Execute" id="GetAllButton">
        SELECT * from table
    </div>
    <div class="queryLine">
        <input class="button" type="button" value="Execute" id="GetByCodeButton">
        SELECT * Where
        <select style="text-align: center; font-weight: bold; border-radius: 5px" id="Selector1">
            <option value="code">код ЖА</option>
            <option value="fullName">полное наименование ЖА</option>
            <option value="abbreviation">аббревиатура ЖА</option>
        </select>
        =
        <input class="inputCustomSmall" id="GetByCodeField" placeholder="value" value="0">
    </div>
    <div class="queryLine">
        <input class="button" type="button" value="Execute" id="InsertButton">
        Insert into table values:
        'код ЖА'=
        <input class="inputCustomSmall" id="CodeField" value="0">
        ,'полное наименование ЖА'=
        <input class="inputCustomLarge" id="FullNameField">
        ,'аббревиатура ЖА'=
        <input class="inputCustomSmall" id="ABField">
    </div>
    <div class="queryLine">
        <input class="button" type="button" value="Execute" id="DeleteButton">
        Delete from table where 'код ЖА' =
        <input class="inputCustomSmall" id="DeleteField" value="0">
    </div>
    <div class="queryLine">
        <input class="button" type="button" value="Execute" id="UpdateButton">
        Update table set
        'код ЖА'=
        <input class="inputCustomSmall" id="UpdateCodeField" value="0">
        ,'полное наименование ЖА'=
        <input class="inputCustomLarge" id="UpdateFullNameField">
        ,'аббревиатура ЖА'=
        <input class="inputCustomSmall" id="UpdateABField">
        ,where 'код ЖА'=
        <input class="inputCustomSmall" id="UpdateCodeField2" value="0">
    </div>
</div>
<div style="display: flex; justify-content: center; margin-left: auto; margin-right: auto; margin-top: 40px; font-weight: bold; font-size: xx-large; user-select: none;">Server response:</div>
<div class="response">
    <pre style="margin-left: 10px"  id="result"></pre>
</div>
</body>
</html>
