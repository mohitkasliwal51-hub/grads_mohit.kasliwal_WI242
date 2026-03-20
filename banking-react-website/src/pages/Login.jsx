import { useState } from 'react';
import { Link } from 'react-router-dom';
import usePageTitle from '../hooks/usePageTitle';

export default function Login({ showAlert }) {
  usePageTitle('Netbanking Login | SBI Digital');

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [touched, setTouched] = useState({ username: false, password: false });

  const usernameInvalid = touched.username && username.trim() === '';
  const passwordInvalid = touched.password && password.trim() === '';

  const handleSubmit = (event) => {
    event.preventDefault();
    setTouched({ username: true, password: true });

    if (username.trim() === '' || password.trim() === '') {
      return;
    }

    showAlert('Login successful!', 'success');
  };

  return (
    <div className="container">
      <h2 className="mb-3">Netbanking Login</h2>
      <hr />

      <form onSubmit={handleSubmit} className="card shadow-sm p-4 col-md-6 mx-auto" noValidate>
        <div className="mb-3">
          <label htmlFor="username" className="form-label">Username:</label>
          <input
            type="text"
            id="username"
            name="un"
            className={`form-control ${usernameInvalid ? 'is-invalid' : ''}`}
            value={username}
            onChange={(event) => setUsername(event.target.value)}
            onBlur={() => setTouched((prev) => ({ ...prev, username: true }))}
            required
          />
          <div className="invalid-feedback">Please enter a valid username.</div>
        </div>

        <div className="mb-3">
          <label htmlFor="password" className="form-label">Password:</label>
          <input
            type="password"
            id="password"
            name="pwd"
            className={`form-control ${passwordInvalid ? 'is-invalid' : ''}`}
            value={password}
            onChange={(event) => setPassword(event.target.value)}
            onBlur={() => setTouched((prev) => ({ ...prev, password: true }))}
            required
          />
          <div className="invalid-feedback">Password cannot be empty.</div>
        </div>

        <div className="d-flex gap-2">
          <button type="submit" className="btn btn-primary">LOGIN</button>
          <button
            type="button"
            className="btn btn-outline-secondary"
            onClick={() => {
              setUsername('');
              setPassword('');
              setTouched({ username: false, password: false });
            }}
          >
            RESET
          </button>
        </div>
      </form>

      <p className="mt-4 text-center">
        New to SBI? <Link to="/signup" className="btn btn-link p-0 align-baseline">Sign Up Here</Link>
      </p>
    </div>
  );
}
