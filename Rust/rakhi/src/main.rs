use nannou::prelude::*;

fn main() {
    nannou::app(model).update(update).run();
}

struct Model {
    _window: window::Id,
}

fn model(app: &App) -> Model {
    let _window = app.new_window().view(view).build().unwrap();
    Model { _window }
}

fn update(_app: &App, _model: &mut Model, _update: Update) {}

fn view(app: &App, _model: &Model, frame: Frame) {

    let draw = app.draw();
    draw.background().color(PURPLE);

    let line_points = (0..50).map(|i| {
        let x = i as f32 - 25.0;
        let point = pt2(x, x.sin()) * 20.0;
        (point, LIGHTGREEN)
    });

    draw.polyline()
        .weight(7.0)
        .points_colored(line_points);


    let radius = 150.0;
    let polygon_points = (0..=360).step_by(45).map(|i| {
        let radian = deg_to_rad(i as f32);
        let x = radian.sin() * radius;
        let y = radian.cos() * radius;
        (pt2(x, y), STEELBLUE)
    });
    draw.polygon().points_colored(polygon_points);


    draw.rect().x_y(0.0, 0.0)
               .w_h(200.0, 200.0)
               .z_degrees(45.0)
               .color(RED);


    draw.ellipse().color(YELLOW);

    draw.to_frame(app, &frame).unwrap();
}

