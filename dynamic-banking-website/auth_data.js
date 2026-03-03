const loginForm = `
    <h2>Netbanking Login</h2>
    <hr>
    <form id="login-form" onsubmit="return validateLogin(event)">
        <div class="form-group">
            <label for="username">Username:</label><br>
            <input type="text" id="username" name="un" required aria-required="true">
        </div>
        <div class="form-group">
            <label for="password">Password:</label><br>
            <input type="password" id="password" name="pwd" required aria-required="true">
        </div>
        <br>
        <button type="submit" class="sbi-btn">LOGIN</button>
        <button type="reset" class="reset-btn">RESET</button>
    </form>
    <p style="margin-top:20px;">New to SBI? <button onclick="loadPage('signup')" class="link-btn">Sign Up Here</button></p>
`;

const signupForm = `
    <h2>Create New Account</h2>
    <hr>
    <form id="signup-form" onsubmit="return validateSignup(event)">
        <label for="new-un">Choose Username:</label><br>
        <input type="text" id="new-un" required><br><br>
        
        <label for="new-pwd">Create Password:</label><br>
        <input type="password" id="new-pwd" required><br><br>
        
        <label for="confirm-pwd">Confirm Password:</label><br>
        <input type="password" id="confirm-pwd" required><br><br>
        
        <button type="submit" class="sbi-btn">REGISTER</button>
    </form>
    <p style="margin-top:20px;">Already have an account? <button onclick="loadPage('netbanking')" class="link-btn">Back to Login</button></p>
`;