const loginForm = `
    <div class="container">
        <h2 class="mb-3">Netbanking Login</h2>
        <hr>

        <form id="login-form" 
              onsubmit="return validateLogin(event)" 
              class="card shadow-sm p-4 col-md-6 mx-auto">

<div class="mb-3">
    <label for="username" class="form-label">Username:</label>
    <input type="text"
           id="username"
           name="un"
           required
           aria-required="true"
           class="form-control">
    <div class="invalid-feedback">
        Please enter a valid username.
    </div>
</div>

<div class="mb-3">
    <label for="password" class="form-label">Password:</label>
    <input type="password"
           id="password"
           name="pwd"
           required
           aria-required="true"
           class="form-control">
    <div class="invalid-feedback">
        Password cannot be empty.
    </div>
</div>

            <div class="d-flex gap-2">
                <button type="submit"
                        class="btn btn-primary">
                    LOGIN
                </button>

                <button type="reset"
                        class="btn btn-outline-secondary">
                    RESET
                </button>
            </div>
        </form>

        <p class="mt-4 text-center">
            New to SBI?
            <button onclick="loadPage('signup')" 
                    class="btn btn-link p-0 align-baseline">
                Sign Up Here
            </button>
        </p>
    </div>
`;

const signupForm = `
    <div class="container">
        <h2 class="mb-3">Create New Account</h2>
        <hr>

        <form id="signup-form" 
              onsubmit="return validateSignup(event)" 
              class="card shadow-sm p-4 col-md-6 mx-auto">

            <div class="mb-3">
                <label for="new-un" class="form-label">
                    Choose Username:
                </label>
                <input type="text"
                       id="new-un"
                       required
                       class="form-control">
                <div class="invalid-feedback">
                    Username is required.
                </div>
            </div>

            <div class="mb-3">
                <label for="new-pwd" class="form-label">
                    Create Password:
                </label>
                <input type="password"
                       id="new-pwd"
                       required
                       class="form-control">
                <div class="invalid-feedback">
                    Password is required.
                </div>
            </div>

            <div class="mb-3">
                <label for="confirm-pwd" class="form-label">
                    Confirm Password:
                </label>
                <input type="password"
                       id="confirm-pwd"
                       required
                       class="form-control">
                <div class="invalid-feedback">
                    Passwords must match.
                </div>
            </div>

            <button type="submit"
                    class="btn btn-success">
                REGISTER
            </button>
        </form>

        <p class="mt-4 text-center">
            Already have an account?
            <button onclick="loadPage('netbanking')" 
                    class="btn btn-link p-0 align-baseline">
                Back to Login
            </button>
        </p>
    </div>
`;