<#import "parts/common.ftl" as c>

<@c.page>
<div class="form-row">
    <div class="form-group col-md-6">
        <form class="form-inline" action="/main" method="get">
            <input class="form-control" type="text" name="filter" value="${filter!}" placeholder="Search"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button class="btn btn-primary ml-2" type="submit">Search</button>
        </form>
    </div>
</div>

<a class="btn btn-primary" data-toggle="collapse" href="#collapseFromMessage" role="button" aria-expanded="false"
   aria-controls="collapseExample">New message
</a>
<div class="collapse" id="collapseFromMessage">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="from-group mt-3">
                <input class="form-control" type="text" name="text" placeholder="Input message"/>
            </div>
            <div class="from-group mt-3">
                <input class="form-control" type="text" name="tag" placeholder="Tag"/>
            </div>
            <div class="from-group mt-3">
                <div class="custom-file">
                    <input type="file" id="customFile" name="file"/>
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

            <div class="form-group mt-3">
                <button class="btn btn-primary" type="submit">Add</button>
            </div>
        </form>
    </div>
</div>
<div class="card-columns">
    <#list messages as message>
        <div class="card my-3">
            <#if message.filename?? >
                <img class="card-img-top" src="/img/${message.filename}" alt="">
            </#if>
            <div class="m-2">
                <span>${message.text}</span>
                <i>${message.tag}</i>
                <div class="card-footer text-muted">
                ${message.authorName}
                </div>
            </div>
        </div>

    <#else >
        No message
    </#list>
</div>


</@c.page>