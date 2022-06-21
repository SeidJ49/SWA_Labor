import React from "react";
import { withStyles } from '@material-ui/core/styles';


const styles = theme => ({
	center: {
		display: 'block',
		padding: '10px',
		border: '5px solid orange',
		background: '#e6e6e6',
	}
});

class Comments extends React.Component {

	constructor(props) {
		super(props);
	    this.state = {	  
            data: [],
            ownPosts: false,
            noPosition: false,
            loadingPosts: true
		};		
	}
	
  
	render() {
		const { classes } = this.props;
		return (
		<div className={classes.center}>
			<h4>Here goes the comments list plus buttons for adding and voting on comments.</h4>
	    </div>
		);
	}
}

export default withStyles(styles)(Comments);

