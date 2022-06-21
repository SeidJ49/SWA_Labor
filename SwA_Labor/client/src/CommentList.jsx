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

class CommentList extends React.Component {

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
			<h1>This component renders a list of comment components</h1>
			<Comment></Comment>
	    </div>
		);
	}
}

export default withStyles(styles)(CommentList);

