import React from "react";
import { withStyles } from '@material-ui/core/styles';

import CommentForm from './CommentForm'

const styles = theme => ({
	center: {
		display: 'flex', 
		justifyContent: 'center',
		alignItems: 'center',
		minHeight: '100vh',
	}
});

class Comment extends React.Component {

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
			<h1>This component renders a single comment</h1>
			<CommentForm></CommentForm>
	    </div>
		);
	}
}

export default withStyles(styles)(Comment);

