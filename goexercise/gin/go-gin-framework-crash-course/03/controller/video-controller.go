package controller

import (
	"../entity"
	"../service"
	"github.com/gin-gonic/gin"
)

type VideoController interface {
	FindAll() []entity.Video
	Save(ctx *gin.Context) error
}

type videoController struct {
	service service.VideoService
}

func (controller *videoController) FindAll() []entity.Video {
	return controller.service.FindAll()
}

func (controller *videoController) Save(ctx *gin.Context) error {
	var video entity.Video
	err := ctx.ShouldBindJSON(&video)
	if err != nil {
		return err
	}
	controller.service.Save(video)
	return nil
}

func New(service service.VideoService) VideoController {
	return &videoController{
		service: service,
	}
}
