# Making the Request Parameter Optional using 'required' argument
## Link: https://www.baeldung.com/spring-request-param

<pre>
@GetMapping("/api/foos")
@ResponseBody
public String getFoos(@RequestParam(required = false) String id) { 
    return "ID: " + id;
}
</pre>
