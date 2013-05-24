package com.aura.deploy.engine.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.aura.base.container.AbstractContainerElement;

public class FrameImageCE extends AbstractContainerElement {
	public FrameImageCE(int id, String label) {
		super(id, label);
	}

	private BufferedImage bi;
	public BufferedImage getImage() {
		try {
			if (bi == null) {
				bi = ImageIO.read(new File(getLabel()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bi;
	}
}
