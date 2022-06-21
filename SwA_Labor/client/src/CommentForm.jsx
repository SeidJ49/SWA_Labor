import React from "react";
import { withStyles } from '@material-ui/core/styles';


const styles = theme => ({
	center: {
		display: 'flex', 
		justifyContent: 'center',
		alignItems: 'center',
		minHeight: '100vh',
	}
});

class CommentForm extends React.Component {

	constructor(props) {
		super(props);
	    this.state = {	  
            data: [],
		};		
	}
	
	render() {
		const { classes } = this.props;
		return (
		<div className={classes.center}>
			<h1>This component renders the input form for a comment</h1>
	    </div>
		);
	}
}

export default withStyles(styles)(CommentForm);

