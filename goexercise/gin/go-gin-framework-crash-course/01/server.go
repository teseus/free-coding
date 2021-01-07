package main

import (
	"./controller"
	"./service"
	"github.com/gin-gonic/gin"
)

var (
	videoService    service.VideoService       = service.New()
	videoController controller.VideoController = controller.New(videoService)
)

func main() {
	server := gin.Default()

	server.GET("/videos", func(ctx *gin.Context) {
		ctx.JSON(200, videoController.FindAll())
	})

	server.POST("/videos", func(context *gin.Context) {
		context.JSON(200, videoController.Save(context))
	})

	server.Run(":8080")
}
