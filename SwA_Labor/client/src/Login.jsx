import React from "react";
import clsx from 'clsx';
import { withStyles } from '@material-ui/core/styles';
import IconButton from '@material-ui/core/IconButton';
import Input from '@material-ui/core/Input';
import InputLabel from '@material-ui/core/InputLabel';
import InputAdornment from '@material-ui/core/InputAdornment';
import FormControl from '@material-ui/core/FormControl';
import Button from '@material-ui/core/Button';
import Visibility from '@material-ui/icons/Visibility';
import VisibilityOff from '@material-ui/icons/VisibilityOff';

const styles = theme => ({
	center: {
		display: 'flex',
		justifyContent: 'center',
		alignItems: 'center',
		minHeight: '100vh',
	},
	loginbox: {
		textAlign: 'center',
		width: '50ch',
		border: '1px solid gray',
		borderRadius: '3px',
		padding: '10px',
	},
	buttonRightAlign: {
		padding: '20px',
		textAlign: 'right',
	},
	margin: {
	  margin: theme.spacing(1),
	},
	withoutLabel: {
	  marginTop: theme.spacing(3),
	},
	textField: {
	  width: '40ch',
	  padding: '1px',
	},
});

class Login extends React.Component {

	constructor(props) {
		super(props);
	    this.state = {	  
			password: '',
			username: '',
			showPassword: false,
			loginButtonDisabled: true,
		};		
	}
	
	handleChange = (event) => {
	const username = this.state.username;
	  if (event.target.id==="username") {
		this.setState({username: event.target.value});
		if (username !== null && username.length < 2) {
			this.setState({loginButtonDisabled: true });
		} else {
			this.setState({loginButtonDisabled: false });
		}
	  }
	  if (event.target.id==="password") {
		this.setState({password: event.target.value});
	  }
	};
  
	handleClickShowPassword = () => {
	  this.setState({showPassword: !this.state.showPassword });
	};

	status( response ) {
        if ( response.status >= 200 && response.status < 300 ) {
            return Promise.resolve( response )
        } else {
            return Promise.reject( new Error( response.statusText ) )
        }
    }

    processData = ( data ) => {
		let tid = data.id;
		if ( tid !== 0 ) {
			this.props.authorized();
		}
    }

	handleLoginSubmit = ( event ) => {
        var formdata = JSON.stringify( this.state );
        fetch( this.props.url + "users/login", {
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
            },
            method: 'post',
            body: formdata
        })
            .then( this.status )
            .then( function(response) { return response.json() } )
            .then( this.processData )
            .catch( function( error ) {
                console.log( 'Request failed', error );
            });
		event.preventDefault();
    }
  
	render() {
		const { classes } = this.props;
		return (
		<div className={classes.center}>
			<div className={classes.loginbox}>
			<form onSubmit={this.handleLoginSubmit}>
				<FormControl className={clsx(classes.margin, classes.textField)}>
					<InputLabel htmlFor="login">Login</InputLabel>
					<Input
					id="username"
					type='text'
					value={this.state.username}
					onChange={this.handleChange}
					/>
				</FormControl>
				<FormControl className={clsx(classes.margin, classes.textField)}>
					<InputLabel htmlFor="password">Password</InputLabel>
					<Input id="password"
					type={this.state.showPassword ? 'text' : 'password'}
					value={this.state.password}
					onChange={this.handleChange}
					endAdornment={
						<InputAdornment position="end">
						<IconButton
							aria-label="toggle password visibility"
							onClick={this.handleClickShowPassword}
							onMouseDown={this.handleMouseDownPassword}
						>
							{this.state.showPassword ? <Visibility /> : <VisibilityOff />}
						</IconButton>
						</InputAdornment>
					}
					/>
				</FormControl>
				<div className={classes.buttonRightAlign}>
					<Button variant="contained" color="primary" type="submit" value="submit" 
							disabled={this.state.loginButtonDisabled}>
							Login
					</Button>
				</div>
			</form>
			</div>
		</div>
		);
	}
}

export default withStyles(styles)(Login);

