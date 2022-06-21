import React from "react";
import { withStyles } from '@material-ui/core/styles';


const styles = theme => ({
	center: {
		display: 'block',
		border: '5px solid gray',
		padding: '10px',
		marginTop: '10px',
	}
});

class PostForm extends React.Component {

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
			<h4>This component renders the input form for a post</h4>
	    </div>
		);
	}
}

export default withStyles(styles)(PostForm);

