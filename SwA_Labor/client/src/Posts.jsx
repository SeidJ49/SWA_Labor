import React from "react";
import { withStyles } from '@material-ui/core/styles';

import PostList from './PostList'

const styles = theme => ({
	center: {
		display: 'block',
		justifyContent: 'center',
		alignItems: 'center',
		minHeight: 'calc(100vh - 40px)',
		border: '5px solid red',
		padding: '10px',
	}
});

class Posts extends React.Component {

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
			<h1>Here goes the posts list plus buttons for adding and editing posts</h1>
			<PostList></PostList>
	    </div>
		);
	}
}

export default withStyles(styles)(Posts);

