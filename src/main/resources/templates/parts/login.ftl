<#macro login path isRegistrerForm>
<form action="${path}" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Name:</label>
        <div class="col-sm-6">
            <input class="form-control" type="text" name="username" placeholder="User name"/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Password: </label>
        <div class="col-sm-6">
            <input class="form-control" type="password" name="password" placeholder="Password"/>
        </div>
    </div>
    <#if isRegistrerForm>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Email: </label>
            <div class="col-sm-6">
                <input class="form-control" type="email" name="email" placeholder="Email"/>
            </div>
        </div>
    </#if>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <#if !isRegistrerForm> <a href="/registration">Sing up</a> </#if>
    <button class="btn btn-primary" type="submit"><#if !isRegistrerForm>Sing In<#else>Create</#if></button>
</form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button class="btn btn-primary">Sing Out</button>
</form>
</#macro>