<#-- @ftlvariable name="" type="com.temperature.view.RoomsView" -->
<html>
  <body>
    <h1>Rooms:</h1>
    <#list rooms as room>
      <li>${room.name}</li>
    </#list>
  </body>
</html>
