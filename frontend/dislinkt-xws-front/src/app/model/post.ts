export interface Post {
    id: string;
    userId: string;
    textContent: string;
    imagePath: string;
    userLikes: string[];
    userDislikes: string[];
    datePosted: Date;
    commentIds: string[];
}

export interface NewPostDto {
    textContent: string;
    image: File;
}

export interface Comment {
    id: string;
    userId: string;
    textContent: string;
    postId: string;
    datePosted: Date;
}