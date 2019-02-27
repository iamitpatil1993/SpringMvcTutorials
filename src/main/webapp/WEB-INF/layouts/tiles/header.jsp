<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  <!-- This is standard jstl function library, provides useful functions to use. (we used it for endsWith -->

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<a class="navbar-brand" href="<c:url value="/" />">Spittle
		Management</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarText" aria-controls="navbarText"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<!-- we should avoid such conditions to protected url, rather use url property to apply constraints defined in config file as shown below -->
			<sec:authorize access="isAuthenticated() and hasRole('ROLE_SPITTLE')">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> Spittle </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="<c:url value="/spittles" />">Spittles</a>
						<a class="dropdown-item"
							href="<c:url value="/spittles/register" />">Register</a>
					</div></li>
			</sec:authorize>
			<!-- We can specify constraints of which url mappig we want to appy here, since I want to protect /fileupload so I added url as /fileupload so taht spring will apply all constraints declared in config to this expression -->
			<!-- Using this url parameter, helps us to reuse same constraints applied in config to get applied here in order to check access to same url -->
			<!-- So, if tomorrow constrains changes in config file, we do not need to worry, they will get reflected here automatically. -->
			<!-- in contrast if we duplicated constraints here using access property and specifying roles and etc here using SpEL, then constrains will get duplicated in two place -->
			<!-- hence we will need to update all view using same constrains in application when there are any modifications in constraint to access some url -->
			<sec:authorize url="/fileupload">
				<li class="nav-item"><a class="nav-link"
					href="<c:url value="/fileupload" />">File Upload</a></li>
			</sec:authorize>
		</ul>
	</div>

	<div class="collapse navbar-collapse" id="navbarText">
		<ul class="navbar-nav ml-auto">
			<sec:authorize access="!isAuthenticated()">
				<c:set var="uri" scope="page"
					value="${requestScope['javax.servlet.forward.request_uri']}" />
				<c:if test="${!fn:endsWith(uri, '/login')}">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="login" />">Login</a></li>
				</c:if>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#"
					id="navbarDropdownMenuLink" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> <sec:authentication
							property="principal.username" /> <!-- principal here is actually object of type UserDetails.(But note, not always.) -->

				</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="<c:url value="/logout"/>">logout</a>
					</div></li>
			</sec:authorize>

		</ul>

	</div>
	</div>
</nav>