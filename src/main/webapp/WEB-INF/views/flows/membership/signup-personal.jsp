<%@include file="../flows-shared/header.jsp"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="container">


	<div class="row">

		<div class="col-md-6 col-md-offset-3">

			<div class="panel panel-primary">

				<div class="panel-heading">
					<h4>Sign Up</h4>
				</div>

				<div class="panel-body">

					<sf:form method="POST" modelAttribute="user"
						class="form-horizontal" id="registerForm">


						<div class="form-group">
							<label class="control-label col-md-4">First Name</label>
							<div class="col-md-8">
								<sf:input type="text" path="firstName" class="form-control"
									placeholder="First Name" />
								<sf:errors path="firstName" cssClass="help-block" element="em" />
							</div>
						</div>


						<div class="form-group">
							<label class="control-label col-md-4">Last Name</label>
							<div class="col-md-8">
								<sf:input type="text" path="lastName" class="form-control"
									placeholder="Last Name" />
								<sf:errors path="lastName" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4">Email</label>
							<div class="col-md-8">
								<sf:input type="text" path="email" class="form-control"
									placeholder="abc@zyx.com" />
								<sf:errors path="email" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4">Contact Number</label>
							<div class="col-md-8">
								<sf:input type="text" path="contactNumber" class="form-control"
									placeholder="" maxlength="10" />
								<sf:errors path="contactNumber" cssClass="help-block"
									element="em" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4">Address</label>
							<div class="col-md-8">
								<sf:input type="text" path="address" class="form-control"
									placeholder="" maxlength="100" />
								<sf:errors path="address" cssClass="help-block"
									element="em" />
							</div>
						</div>
						
						
						<div class="form-group">
							<label class="control-label col-md-4">City</label>
							<div class="col-md-8">
								<sf:input type="text" path="city" class="form-control"
									placeholder="" maxlength="" />
								<sf:errors path="city" cssClass="help-block"
									element="em" />
							</div>
						</div>
						
						
						<div class="form-group">
							<label class="control-label col-md-4">State</label>
							<div class="col-md-8">
								<sf:input type="text" path="state" class="form-control"
									placeholder="" maxlength="" />
								<sf:errors path="state" cssClass="help-block"
									element="em" />
							</div>
						</div>
						
						
						<div class="form-group">
							<label class="control-label col-md-4">Country</label>
							<div class="col-md-8">
								<sf:input type="text" path="country" class="form-control"
									placeholder="" maxlength="" />
								<sf:errors path="country" cssClass="help-block"
									element="em" />
							</div>
						</div>
						
						
						<div class="form-group">
							<label class="control-label col-md-4">PostalCode</label>
							<div class="col-md-8">
								<sf:input type="text" path="postalCode" class="form-control"
									placeholder="" maxlength="" />
								<sf:errors path="postalCode" cssClass="help-block"
									element="em" />
							</div>
						</div>
						

						<div class="form-group">
							<label class="control-label col-md-4">Password</label>
							<div class="col-md-8">
								<sf:input type="password" path="password" class="form-control"
									placeholder="Password" />
								<sf:errors path="password" cssClass="help-block" element="em" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-4">Confirm Password</label>
							<div class="col-md-8">
								<sf:input type="password" path="confirmPassword" class="form-control"
									placeholder="Re-enter Password" />
								<sf:errors path="confirmPassword" cssClass="help-block" element="em" />
							</div>
						</div>



						<div class="form-group">
							<label class="control-label col-md-4">Select Role</label>
							<div class="col-md-8">
								<label class="radio-inline"> <sf:radiobutton path="role"
										value="USER" checked="checked" /> User
								</label> <label class="radio-inline"> <sf:radiobutton
										path="role" value="LIBRARY" /> Library
								</label>
							</div>
						</div>

						<div class="form-group">
								<div class="col-md-offset-4 col-md-8">
									<button type="submit" name="_eventId_confirm" class="btn btn-primary">
										Confirm<span class="glyphicon glyphicon-chevron-right"></span>
									</button>																	 
								</div>
							</div>


					</sf:form>


				</div>


			</div>


		</div>


	</div>


</div>

<%@include file="../flows-shared/footer.jsp"%>
