package controller

import (
	"../entity"
	"../service"
	"github.com/gin-gonic/gin"
)

type VideoController interface {
	FindAll() []entity.Video
	Save(ctx *gin.Context) entity.Video
}

type videoController struct {
	service service.VideoService
}

func (controller *videoController) FindAll() []entity.Video {
	return controller.service.FindAll()
}

func (controller *videoController) Save(ctx *gin.Context) entity.Video {
	var video entity.Video
	ctx.BindJSON(&video)
	controller.service.Save(video)
	return video
}

func New(service service.VideoService) VideoController {
	return &videoController{
		service: service,
	}
}
