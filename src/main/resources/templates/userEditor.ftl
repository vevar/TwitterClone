<#import "parts/common.ftl" as c>

<@c.page>
User editor
<form action="/user" method="post">
    <input type="hidden" value="${user.id}" name="userId"/>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <label>User name: <input type="text" value="${user.username}" name="username"/></label>
    <#list roles as role>
        <div>
            <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked","")}/>${role}
            </label>
        </div>
    </#list>
    <input type="submit" value="Save"/>
</form>
</@c.page>