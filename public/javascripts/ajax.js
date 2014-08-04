function actualizeFormErrors(error)
{
    if(error.responseText != "")
    {
        var jsonResponseText = $.parseJSON(error.responseText);
        clearFormErrors();
        $.each(jsonResponseText, function(name, val)
        {
            jsonResponseStatus = $.parseJSON(JSON.stringify(val));
             $.each(jsonResponseStatus, function(name2, val2)
             {
                 $("#"+name).parent().append('<dd class="error">' + val2 + '</dd>');
             });
        });
    }
}
function clearFormErrors()
{
    $(".form-control").each(function( index )
    {
        $(this).siblings().remove();
    });
}