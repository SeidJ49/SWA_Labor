import React from "react";
import { withStyles } from '@material-ui/core/styles';

import Comments from './Comments';
import PostForm from './PostForm';

const styles = theme => ({
	center: {
		display: 'block',
		border: '5px solid green',
		padding: '10px',
	}
});

class Post extends React.Component {

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
			<h3>This component renders a single post</h3>
			<Comments></Comments>
            <PostForm></PostForm>
	    </div>
		);
	}
}

export default withStyles(styles)(Post);

